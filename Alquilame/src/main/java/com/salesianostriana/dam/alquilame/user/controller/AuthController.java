package com.salesianostriana.dam.alquilame.user.controller;

import com.salesianostriana.dam.alquilame.security.jwt.JwtProvider;
import com.salesianostriana.dam.alquilame.user.dto.CreateUserDto;
import com.salesianostriana.dam.alquilame.user.dto.LoginResponse;
import com.salesianostriana.dam.alquilame.user.dto.LoginRequest;
import com.salesianostriana.dam.alquilame.user.dto.UserResponse;
import com.salesianostriana.dam.alquilame.user.model.User;
import com.salesianostriana.dam.alquilame.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/register/inquilino")
    public ResponseEntity<UserResponse> createUserWithInquilinoRole(@RequestBody CreateUserDto dto) {
        User user = userService.createUserWithInquilinoRole(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.fromUser(user));
    }

    @PostMapping("/register/propietario")
    public ResponseEntity<UserResponse> createUserWithPropietarioRole(@RequestBody CreateUserDto dto) {
        User user = userService.createUSerWitPropietarioRole(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.fromUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest dto) {
        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                dto.getUsername(),
                                dto.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(LoginResponse.of(user, token));
    }

    //CAMBIAR CONTRASEÑA

}
