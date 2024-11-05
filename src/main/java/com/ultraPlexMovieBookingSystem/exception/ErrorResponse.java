package com.ultraPlexMovieBookingSystem.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private String message;
    private String details;
    private LocalDateTime timestamp;
    private String errorCode;

    public ErrorResponse(String message, String details, String errorCode) {
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
        this.errorCode = errorCode;
    }
}
