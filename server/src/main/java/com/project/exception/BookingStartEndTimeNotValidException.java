package com.project.exception;

public class BookingStartEndTimeNotValidException extends RuntimeException {
    public BookingStartEndTimeNotValidException(String message) {
        super(message);
    }
}
