package com.product.app.exception;

import lombok.Getter;
import lombok.Setter;


public class ECommerceException extends RuntimeException{

    public ECommerceException(String message) {
        super(message);
    }
}
