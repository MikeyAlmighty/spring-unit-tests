package com.example.springunittests.exception;

public class PokemonNotFoundException extends RuntimeException {
    private static final long serialVerisionUID = 1;

    public PokemonNotFoundException(String message) {
        super(message);
    }
}
