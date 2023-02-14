package com.salesianostriana.dam.alquilame.exception;

public class DwellingBadRequestDeleteException extends RuntimeException{

    public DwellingBadRequestDeleteException(Long id) {
        super("The dwelling with id %d you want to delete doesn't exist");
    }

}
