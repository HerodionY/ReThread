package com.reTheard.reThreard.dto;

public class ErrorResponse {
    private String message;
    
    public ErrorResponse(String message) {
        this.message = message;
    }

    // Getter and setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
