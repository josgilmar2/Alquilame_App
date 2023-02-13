package com.salesianostriana.dam.alquilame.dwelling.dto;

import com.salesianostriana.dam.alquilame.dwelling.model.Type;
import com.salesianostriana.dam.alquilame.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class DwellingRequest {

    private String name, address, description, image;

    private Type type;
    private double price, m2;
    private int numBedrooms, numBathrooms;
    private boolean hasElevator, hasPool, hasTerrace, hasGarage;
    private Long provinceId;
    private User user;

}
