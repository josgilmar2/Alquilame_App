package com.salesianostriana.dam.alquilame.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(UUID id) {
        super(String.format("The user with id %s could not be found", id.toString()));
    }

}
