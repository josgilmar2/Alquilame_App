package com.salesianostriana.dam.alquilame.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateUserDto {

    private String username;
    private String fullName;
    private String email;
    private String address;
    private String phoneNumber;
    private String avatar;
    private String password;
    private String verifyPassword;

}
