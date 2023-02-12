package com.salesianostriana.dam.alquilame.dwelling.dto;

import com.salesianostriana.dam.alquilame.dwelling.model.Dwelling;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AllDwellingResponse {

    private Long id;
    private String name, city, image;
    private double price;

    public static AllDwellingResponse of(Dwelling dwelling) {
        return AllDwellingResponse.builder()
                .id(dwelling.getId())
                .name(dwelling.getName())
                .city(dwelling.getCity().getName())
                .image(dwelling.getImage())
                .price(dwelling.getPrice())
                .build();
    }
}
