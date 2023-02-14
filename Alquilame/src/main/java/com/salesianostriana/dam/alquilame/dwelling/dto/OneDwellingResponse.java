package com.salesianostriana.dam.alquilame.dwelling.dto;

import com.salesianostriana.dam.alquilame.dwelling.model.Dwelling;
import com.salesianostriana.dam.alquilame.dwelling.model.Type;
import com.salesianostriana.dam.alquilame.user.dto.UserDwellingResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public class OneDwellingResponse extends AllDwellingResponse{

    private String address, description;
    private Type type;
    private double m2;
    private int numBedrooms, numBathrooms;
    private boolean hasElevator, hasPool, hasTerrace, hasGarage;
    private UserDwellingResponse owner;

    public static OneDwellingResponse of (Dwelling dwelling) {
        return OneDwellingResponse.builder()
                .id(dwelling.getId())
                .name(dwelling.getName())
                .province(dwelling.getProvince().getName())
                .image(dwelling.getImage())
                .price(dwelling.getPrice())
                .address(dwelling.getAddress())
                .description(dwelling.getDescription())
                .type(dwelling.getType())
                .m2(dwelling.getM2())
                .numBedrooms(dwelling.getNumBedrooms())
                .numBathrooms(dwelling.getNumBathrooms())
                .hasElevator(dwelling.isHasElevator())
                .hasPool(dwelling.isHasPool())
                .hasTerrace(dwelling.isHasTerrace())
                .hasGarage(dwelling.isHasGarage())
                .owner(UserDwellingResponse.of(dwelling.getUser()))
                .build();
    }

}
