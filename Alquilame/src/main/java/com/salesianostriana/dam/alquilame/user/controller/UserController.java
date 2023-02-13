package com.salesianostriana.dam.alquilame.user.controller;

import com.salesianostriana.dam.alquilame.page.dto.PageDto;
import com.salesianostriana.dam.alquilame.user.dto.UserResponse;
import com.salesianostriana.dam.alquilame.user.model.User;
import com.salesianostriana.dam.alquilame.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public PageDto<UserResponse> getAllUsers(@RequestParam(value = "search", defaultValue = "") String search,
                                              @PageableDefault(size = 20) Pageable pageable) {

        return new PageDto<>(userService.findAllUsers(search, pageable));
    }

    @GetMapping("/profile")
    public UserResponse getMyProfile(@AuthenticationPrincipal User user) {
        User result = userService.myProfile(user);

        return UserResponse.fromUser(result);
    }

    //VER VIVIENDAS FAVORITAS DEL USUARIO, PUT, DELETE, GET ID USUARIO

}
