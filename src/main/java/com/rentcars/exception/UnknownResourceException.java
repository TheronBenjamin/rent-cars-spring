package com.rentcars.exception;

public class UnknownResourceException extends RuntimeException{

    public UnknownResourceException() {
        super("Unknown resource");
    }

    public UnknownResourceException(String message) {
        super(message);
    }
}
