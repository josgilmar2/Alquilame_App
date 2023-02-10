package com.salesianostriana.dam.alquilame.dwelling.model;

import com.salesianostriana.dam.alquilame.city.model.City;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class Dwelling {

    @Id @GeneratedValue
    private Long id;

    private String name, address, description;

    /*@ElementCollection(fetch = FetchType.EAGER)
    private List<String> images = new ArrayList<>();*/

    private String image;

    @Enumerated(EnumType.STRING)
    private Type type;

    private double price, m2;

    private int numBedrooms, numBathrooms;

    private boolean hasElevator, hasPool, hasTerrace;

    @ManyToOne
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "FK_DWELLING_CITY"))
    private City city;

}
