package com.salesianostriana.dam.alquilame.user.service;

import com.salesianostriana.dam.alquilame.user.dto.CreateUserDto;
import com.salesianostriana.dam.alquilame.user.model.User;
import com.salesianostriana.dam.alquilame.user.model.UserRole;
import com.salesianostriana.dam.alquilame.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Optional;

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

}
