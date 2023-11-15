package com.project.rentAndShareApp.exception;

public class BookingUnsupportedStatusException extends RuntimeException {
    public BookingUnsupportedStatusException(String message) {
        super(message);
    }
}
