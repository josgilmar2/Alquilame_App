package com.salesianostriana.dam.alquilame.dwelling.model;

import com.salesianostriana.dam.alquilame.city.model.City;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class Dwelling {

    @Id @GeneratedValue
    private Long id;

    private String name, address, description, image;

    @Enumerated(EnumType.STRING)
    private Type type;

    private double price, m2;

    private int numBedrooms, numBathrooms;

    private boolean hasElevator, hasPool, hasTerrace;

    @ManyToOne
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "FK_DWELLING_CITY"))
    private City city;

}
