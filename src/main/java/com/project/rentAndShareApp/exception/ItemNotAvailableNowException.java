package com.project.rentAndShareApp.exception;

public class ItemNotAvailableNowException extends RuntimeException{
    public ItemNotAvailableNowException(String message) {
        super(message);
    }
}
