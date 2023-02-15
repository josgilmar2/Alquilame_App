package com.salesianostriana.dam.alquilame.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class EditUserProfileDto {

    private String username;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String avatar;

    //PODRÍA ELIMINAR EL AVATAR PARA LUEGO HACER PETICIÓN PUT A LA HORA DE SUBIR FICHERO
}
