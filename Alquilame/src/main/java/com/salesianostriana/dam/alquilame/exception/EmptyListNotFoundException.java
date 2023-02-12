package com.salesianostriana.dam.alquilame.exception;

public class EmptyListNotFoundException extends RuntimeException{

    public EmptyListNotFoundException(Class clazz) {
        super(String.format("No se puede encontrar datos del tipo %s.", clazz.getName().split("\\.")[4]));
    }

}
