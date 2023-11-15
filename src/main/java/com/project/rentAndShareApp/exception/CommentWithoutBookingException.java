package com.project.rentAndShareApp.exception;

public class CommentWithoutBookingException extends RuntimeException{
    public CommentWithoutBookingException(String message) {
        super(message);
    }
}
