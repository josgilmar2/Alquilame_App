package com.salesianostriana.dam.alquilame.dwelling.dto;

import com.salesianostriana.dam.alquilame.dwelling.model.Type;
import com.salesianostriana.dam.alquilame.user.model.User;
import com.salesianostriana.dam.alquilame.validation.annotation.dwelling.OnlyThreeTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class DwellingRequest {

    @NotEmpty(message = "{dwellingRequest.name.notempty}")
    private String name;

    @NotEmpty(message = "{dwellingRequest.address.notEmpty}")
    private String address;

    private String description;

    private String image;

    private Type type;

    @NotNull(message = "{dwellingRequest.price.notempty}")
    @Min(value = 100, message = "{dwellingRequest.price.min}")
    private double price;

    private double m2;
    private int numBedrooms, numBathrooms;
    private boolean hasElevator, hasPool, hasTerrace, hasGarage;

    @NotNull(message = "{dwellingRequest.provinceId.notnull}")
    private Long provinceId;

}
