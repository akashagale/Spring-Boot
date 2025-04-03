package com.app.ecommerce.exception.payload;


import java.util.Date;

public class ApiResponse {
    private String message;
    private boolean success;
    private Date date;

    public ApiResponse(String message, boolean success, Date date) {
        this.message = message;
        this.success = success;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }





}
