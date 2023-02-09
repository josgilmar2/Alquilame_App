package com.salesianostriana.dam.alquilame.city.model;

import com.salesianostriana.dam.alquilame.dwelling.model.Dwelling;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class City {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Dwelling> dwellings = new ArrayList<>();

}
