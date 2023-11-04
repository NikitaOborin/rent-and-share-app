package com.project.rentAndShareApp.exception;

import lombok.extern.slf4j.Slf4j;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handle(IllegalArgumentException exception) {
        log.error("IllegalArgumentException: " + exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(NotFoundException exception) {
        log.error("NotFoundException: " + exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(MethodArgumentNotValidException exception) {
        String defaultMessage = Objects.requireNonNull(exception.getFieldError()).getDefaultMessage();
        log.error("ValidException: " + defaultMessage);
        return new ResponseEntity<>(defaultMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(MissingRequestHeaderException exception) {
        log.error("MissingRequestHeaderException: " + exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(UserEmailAlreadyExistException exception) {
        log.error("UserEmailAlreadyExistException: " + exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
