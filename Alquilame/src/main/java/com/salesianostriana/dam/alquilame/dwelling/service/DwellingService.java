package com.salesianostriana.dam.alquilame.dwelling.service;

import com.salesianostriana.dam.alquilame.city.model.City;
import com.salesianostriana.dam.alquilame.city.service.CityService;
import com.salesianostriana.dam.alquilame.dwelling.dto.AllDwellingResponse;
import com.salesianostriana.dam.alquilame.dwelling.dto.DwellingRequest;
import com.salesianostriana.dam.alquilame.dwelling.dto.OneDwellingResponse;
import com.salesianostriana.dam.alquilame.dwelling.model.Dwelling;
import com.salesianostriana.dam.alquilame.dwelling.repo.DwellingRepository;
import com.salesianostriana.dam.alquilame.exception.DwellingNotFoundException;
import com.salesianostriana.dam.alquilame.exception.EmptyListNotFoundException;
import com.salesianostriana.dam.alquilame.exception.UserDwellingsNotFoundException;
import com.salesianostriana.dam.alquilame.search.spec.GenericSpecificationBuilder;
import com.salesianostriana.dam.alquilame.search.util.SearchCriteria;
import com.salesianostriana.dam.alquilame.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.alquilame.user.dto.UserResponse;
import com.salesianostriana.dam.alquilame.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DwellingService {

    private final DwellingRepository dwellingRepository;
    private final CityService cityService;

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

    public Page<AllDwellingResponse> findUserDwellings(String username, Pageable pageable) {
        Page<AllDwellingResponse> result = dwellingRepository.findAllUserDwellings(username, pageable);
        if(result.isEmpty())
            throw new UserDwellingsNotFoundException(username);
        return result;
    }

    public Dwelling createDwelling(DwellingRequest dto, User user) {

        City toAdd = cityService.findById(dto.getProvinceId());

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
                .user(user)
                .build();

        result.addCity(toAdd);

        return dwellingRepository.save(result);

    }

}
