package com.salesianostriana.dam.alquilame.exception;

public class ProvinceNotFoundException extends RuntimeException{

    public ProvinceNotFoundException(Long id) {
        super(String.format("No province with id %d was found.", id));
    }

}
