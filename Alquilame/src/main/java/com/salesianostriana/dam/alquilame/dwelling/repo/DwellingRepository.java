package com.salesianostriana.dam.alquilame.dwelling.repo;

import com.salesianostriana.dam.alquilame.dwelling.model.Dwelling;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DwellingRepository extends JpaRepository<Dwelling, Long>, JpaSpecificationExecutor<Dwelling> {

    @EntityGraph(value = "city-with-dwelling")
    List<Dwelling> findAll();
}
