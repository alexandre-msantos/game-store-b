package com.alexandre.gamestore.services.exceptions;

public class DataIntegrityViolationException extends RuntimeException{

    public DataIntegrityViolationException(String message) {
        super(message);
    }
}
