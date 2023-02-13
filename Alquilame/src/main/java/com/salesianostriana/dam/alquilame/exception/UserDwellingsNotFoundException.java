package com.salesianostriana.dam.alquilame.exception;

public class UserDwellingsNotFoundException extends RuntimeException{

    public UserDwellingsNotFoundException(String username) {
        super(String.format("No dwellings were found for user: %s", username));
    }

}
