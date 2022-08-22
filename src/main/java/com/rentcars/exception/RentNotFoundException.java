package com.rentcars.exception;

public class RentNotFoundException extends RuntimeException{

    public RentNotFoundException() {
        super("Rent not found");
    }

    public RentNotFoundException(String message) {
        super(message);
    }
}
