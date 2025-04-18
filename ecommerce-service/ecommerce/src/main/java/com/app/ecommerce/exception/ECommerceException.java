package com.app.ecommerce.exception;


import org.springframework.http.HttpStatus;

public class ECommerceException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public ECommerceException(String message) {
        super(message);
    }
    public ECommerceException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
