package com.salesianostriana.dam.alquilame.city.service;

import com.salesianostriana.dam.alquilame.city.model.City;
import com.salesianostriana.dam.alquilame.city.repo.CityRepository;
import com.salesianostriana.dam.alquilame.exception.CityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public City findById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

}
