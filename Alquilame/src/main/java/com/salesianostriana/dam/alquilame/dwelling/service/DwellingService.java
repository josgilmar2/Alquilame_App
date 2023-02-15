package com.salesianostriana.dam.alquilame.dwelling.service;

import com.salesianostriana.dam.alquilame.exception.dwelling.DwellingAccessDeniedException;
import com.salesianostriana.dam.alquilame.exception.dwelling.DwellingNotFoundException;
import com.salesianostriana.dam.alquilame.exception.favourite.FavouriteAlreadyInListException;
import com.salesianostriana.dam.alquilame.exception.province.ProvinceNotFoundException;
import com.salesianostriana.dam.alquilame.exception.user.UserDwellingsNotFoundException;
import com.salesianostriana.dam.alquilame.exception.user.UserNotFoundException;
import com.salesianostriana.dam.alquilame.province.model.Province;
import com.salesianostriana.dam.alquilame.province.service.ProvinceService;
import com.salesianostriana.dam.alquilame.dwelling.dto.AllDwellingResponse;
import com.salesianostriana.dam.alquilame.dwelling.dto.DwellingRequest;
import com.salesianostriana.dam.alquilame.dwelling.model.Dwelling;
import com.salesianostriana.dam.alquilame.dwelling.repo.DwellingRepository;
import com.salesianostriana.dam.alquilame.exception.*;
import com.salesianostriana.dam.alquilame.search.spec.GenericSpecificationBuilder;
import com.salesianostriana.dam.alquilame.search.util.SearchCriteria;
import com.salesianostriana.dam.alquilame.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.alquilame.user.model.User;
import com.salesianostriana.dam.alquilame.user.repo.UserRepository;
import com.salesianostriana.dam.alquilame.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DwellingService {

    private final DwellingRepository dwellingRepository;
    private final ProvinceService provinceService;
    private final UserService userService;
    private final UserRepository userRepository;

    public Page<AllDwellingResponse> search(List<SearchCriteria> params, Pageable pageable) {
        GenericSpecificationBuilder<Dwelling> dwellingGenericSpecificationBuilder =
                new GenericSpecificationBuilder<>(params, Dwelling.class);
        Specification<Dwelling> specification = dwellingGenericSpecificationBuilder.build();
        return dwellingRepository.findAll(specification, pageable).map(AllDwellingResponse::of);
    }

    public Page<AllDwellingResponse> findAllDwellings(String search, Pageable pageable) {
        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);
        Page<AllDwellingResponse> result = search(params, pageable);
        if(result.isEmpty())
            throw new EmptyListNotFoundException(Dwelling.class);
        return result;
    }

    public Dwelling findOneDwelling(Long id) {
        return dwellingRepository.findById(id)
                .orElseThrow(() -> new DwellingNotFoundException(id));
    }

    public Page<AllDwellingResponse> findUserDwellings(User user, Pageable pageable) {
        Page<AllDwellingResponse> result = dwellingRepository.findAllUserDwellings(user.getUsername(), pageable);
        if(result.isEmpty())
            throw new UserDwellingsNotFoundException(user.getUsername());
        return result;
    }

    public Dwelling createDwelling(DwellingRequest dto, User user) {

        Province toAdd = provinceService.findById(dto.getProvinceId());
        User user1 = userService.findUserWithDwellings(user.getId())
                .orElseThrow(() -> new UserNotFoundException(user.getId()));

        Dwelling result = Dwelling.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .description(dto.getDescription())
                .image(dto.getImage())
                .type(dto.getType())
                .price(dto.getPrice())
                .m2(dto.getM2())
                .numBathrooms(dto.getNumBathrooms())
                .numBedrooms(dto.getNumBedrooms())
                .hasElevator(dto.isHasElevator())
                .hasTerrace(dto.isHasTerrace())
                .hasGarage(dto.isHasGarage())
                .hasPool(dto.isHasPool())
                .build();

        result.addCity(toAdd);
        result.addUser(user1);

        return dwellingRepository.save(result);

    }
    public Dwelling editDwelling(Long id, DwellingRequest dto, User user) {

        Province toEdit = provinceService.findById(dto.getProvinceId());

        if(user.getUsername().equalsIgnoreCase(findOneDwelling(id).getUser().getUsername())) {
            return dwellingRepository.findById(id)
                    .map(dwelling -> {
                        dwelling.setName(dto.getName());
                        dwelling.setAddress(dto.getAddress());
                        dwelling.setDescription(dto.getDescription());
                        dwelling.setImage(dto.getImage());
                        dwelling.setType(dto.getType());
                        dwelling.setPrice(dto.getPrice());
                        dwelling.setM2(dto.getM2());
                        dwelling.setNumBathrooms(dto.getNumBathrooms());
                        dwelling.setNumBedrooms(dto.getNumBedrooms());
                        dwelling.setHasElevator(dto.isHasElevator());
                        dwelling.setHasTerrace(dto.isHasTerrace());
                        dwelling.setHasGarage(dto.isHasGarage());
                        dwelling.setHasPool(dto.isHasPool());
                        dwelling.setProvince(toEdit);
                        dwelling.setUser(user);
                        return dwellingRepository.save(dwelling);
                    }).orElseThrow(() -> new DwellingNotFoundException(id));
        } else {
            throw new DwellingAccessDeniedException("You cannot edit dwelling with id: " + id + " because it isn't yours.");
        }
    }

    public void deleteOneDwelling(Long id, User user) {
        Dwelling toDelete = dwellingRepository.findById(id)
                .orElseThrow(() -> new DwellingNotFoundException(id));

        User user1 = userService.findUserWithDwellings(user.getId())
                .orElseThrow(() -> new UserNotFoundException(user.getId()));

        if(!user1.getDwellings().contains(toDelete))
            throw new DwellingAccessDeniedException("You cannot delete dwelling with id: " + id + " because it isn't yours.");

        List<User> favUserDwellings = userService.findFavouriteUserDwellings(id);
        for (User u: favUserDwellings) {
            u.getFavourites().remove(toDelete);
            userService.save(u);
        }
        user1.getFavourites().remove(toDelete);
        userService.save(user1);
        dwellingRepository.delete(toDelete);
    }

    public Dwelling doFavourite(Long id, User user) {
        Dwelling toMarkAsFavourite = findOneDwelling(id);
        User user1 = userRepository.findUserFavouriteDwellings(user.getId())
                .orElseThrow(() -> new UserNotFoundException(user.getId()));

        if(userService.existFavourite(user.getId(), id))
            throw new FavouriteAlreadyInListException(id, user.getUsername());

        user1.getFavourites().add(toMarkAsFavourite);
        dwellingRepository.save(toMarkAsFavourite);
        userService.save(user);

        return toMarkAsFavourite;
    }

    public void deleteFavourite(Long id, User user) {
        Dwelling toDeleteFavourite = findOneDwelling(id);
        User user1 = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException(user.getId()));

        user1.getFavourites().remove(toDeleteFavourite);
        userService.save(user1);

    }

    public Page<AllDwellingResponse> findByProvinceId(Long id, Pageable pageable) {
        Page<AllDwellingResponse> result = dwellingRepository.findByProvinceId(id, pageable);

        if(result.isEmpty())
            throw new ProvinceNotFoundException(id);
        return result;
    }

}
