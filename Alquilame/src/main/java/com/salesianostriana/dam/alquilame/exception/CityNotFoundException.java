package com.salesianostriana.dam.alquilame.exception;

public class CityNotFoundException extends RuntimeException{

    public CityNotFoundException(Long id) {
        super(String.format("No city with id %d was found.", id));
    }

}
