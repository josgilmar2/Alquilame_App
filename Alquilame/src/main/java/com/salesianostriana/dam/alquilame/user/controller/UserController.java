package com.salesianostriana.dam.alquilame.user.controller;

import com.salesianostriana.dam.alquilame.dwelling.dto.AllDwellingResponse;
import com.salesianostriana.dam.alquilame.page.dto.PageDto;
import com.salesianostriana.dam.alquilame.user.dto.EditPasswordDto;
import com.salesianostriana.dam.alquilame.user.dto.EditUserProfileDto;
import com.salesianostriana.dam.alquilame.user.dto.UserResponse;
import com.salesianostriana.dam.alquilame.user.model.User;
import com.salesianostriana.dam.alquilame.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/favourites")
    public PageDto<AllDwellingResponse> getFavourites(@AuthenticationPrincipal User user,
                                                      @PageableDefault(size = 20) Pageable pageable) {
        return new PageDto<>(userService.findFavourites(user, pageable));
    }

    @PutMapping("/")
    public UserResponse editProfile(@Valid @RequestBody EditUserProfileDto dto, @AuthenticationPrincipal User user) {
        User toEdit = userService.edit(dto, user);

        return UserResponse.fromUser(toEdit);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProfile(@AuthenticationPrincipal User user) {
        userService.delete(user);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/changePassword")
    public UserResponse editPassword(@Valid @RequestBody EditPasswordDto dto, @AuthenticationPrincipal User user) {
        User toEditPassword = userService.editPassword(dto, user);

        return UserResponse.fromUser(toEditPassword);
    }

}
