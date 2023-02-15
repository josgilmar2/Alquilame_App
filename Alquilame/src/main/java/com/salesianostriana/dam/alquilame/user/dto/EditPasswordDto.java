package com.salesianostriana.dam.alquilame.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class EditPasswordDto {

    private String oldPassword;
    private String newPassword;
    private String verifyNewPassword;

}
