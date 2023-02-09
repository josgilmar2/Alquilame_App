package com.salesianostriana.dam.alquilame.dwelling.repo;

import com.salesianostriana.dam.alquilame.dwelling.model.Dwelling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DwellingRepository extends JpaRepository<Dwelling, Long> {
}
