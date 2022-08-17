package com.rentcars.exception;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException() {
        super("Client not found");
    }

    public ClientNotFoundException(String message) {
        super(message);
    }
}
