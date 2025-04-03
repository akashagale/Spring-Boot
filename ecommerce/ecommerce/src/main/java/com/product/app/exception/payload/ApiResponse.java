package com.product.app.exception.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ApiResponse {
    private String message;
    private boolean success;
    private Date date;

    public ApiResponse(String message, boolean success, Date date) {
        this.message = message;
        this.success = success;
        this.date = date;
    }
}
