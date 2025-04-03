package com.product.app.exception.handler;

import com.product.app.exception.ECommerceException;
import com.product.app.exception.ResourceNotFoundException;
import com.product.app.exception.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ECommerceException.class)
    public ResponseEntity<ApiResponse> handleECommerceException(ECommerceException ex) {
        String message = ex.getMessage().toString();
        Date date = new Date();
        ApiResponse payload = new ApiResponse(message, false, date);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(payload);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage().toString();
        Date date = new Date();
        ApiResponse payload = new ApiResponse(message, false, date);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(payload);
    }
}