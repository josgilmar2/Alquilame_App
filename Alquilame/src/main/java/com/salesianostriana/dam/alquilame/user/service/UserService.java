package com.salesianostriana.dam.alquilame.user.service;

import com.salesianostriana.dam.alquilame.exception.EmptyListNotFoundException;
import com.salesianostriana.dam.alquilame.exception.UserNotFoundException;
import com.salesianostriana.dam.alquilame.page.dto.PageDto;
import com.salesianostriana.dam.alquilame.search.spec.GenericSpecificationBuilder;
import com.salesianostriana.dam.alquilame.search.util.SearchCriteria;
import com.salesianostriana.dam.alquilame.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.alquilame.user.dto.CreateUserDto;
import com.salesianostriana.dam.alquilame.user.dto.UserResponse;
import com.salesianostriana.dam.alquilame.user.model.User;
import com.salesianostriana.dam.alquilame.user.model.UserRole;
import com.salesianostriana.dam.alquilame.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User createUser(CreateUserDto dto, EnumSet<UserRole> roles) {
        User result = User.builder()
                .username(dto.getUsername())
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .avatar(dto.getAvatar())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(roles)
                .build();

        return userRepository.save(result);
    }

    public User createUserWithInquilinoRole(CreateUserDto dto) {
        return createUser(dto, EnumSet.of(UserRole.INQUILINO));
    }

    public User createUSerWitPropietarioRole(CreateUserDto dto) {
        return createUser(dto, EnumSet.of(UserRole.PROPIETARIO));
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public Page<UserResponse> search(List<SearchCriteria> params, Pageable pageable) {
        GenericSpecificationBuilder<User> productSpeceficationBuilder =
                new GenericSpecificationBuilder<>(params, User.class);
        Specification<User> specification = productSpeceficationBuilder.build();
        return userRepository.findAll(specification, pageable).map(UserResponse::fromUser);
    }

    public Page<UserResponse> findAllUsers(String search, Pageable pageable) {
        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);
        Page<UserResponse> result = search(params, pageable);
        if(result.isEmpty())
            throw new EmptyListNotFoundException(User.class);
        return result;
    }

    public User myProfile(User user) {
        User result = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException(user.getId()));
        return result;
    }

}
