package com.project.rentAndShareApp.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String errorMessage;

    public ErrorResponse(LocalDateTime timestamp, int status, String error, String errorMessage) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.errorMessage = errorMessage;
    }
}