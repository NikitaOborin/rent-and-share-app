package com.project.rentAndShareApp.exception;

public class BookingStartEndTimeNotValidException extends RuntimeException {
    public BookingStartEndTimeNotValidException(String message) {
        super(message);
    }
}
