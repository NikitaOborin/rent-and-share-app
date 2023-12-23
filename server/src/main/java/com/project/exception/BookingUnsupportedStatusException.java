package com.project.exception;

public class BookingUnsupportedStatusException extends RuntimeException {
    public BookingUnsupportedStatusException(String message) {
        super(message);
    }
}
