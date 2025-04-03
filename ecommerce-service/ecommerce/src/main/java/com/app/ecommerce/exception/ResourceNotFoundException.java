package com.app.ecommerce.exception;


public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(String.format("Resource not found: %s", message));
    }
}
