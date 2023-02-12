package com.salesianostriana.dam.alquilame.exception;

public class EmptyListNotFoundException extends RuntimeException{

    public EmptyListNotFoundException(Class clazz) {
        super(String.format("No se puede encontrar %s ", clazz.getName()));
    }

}
