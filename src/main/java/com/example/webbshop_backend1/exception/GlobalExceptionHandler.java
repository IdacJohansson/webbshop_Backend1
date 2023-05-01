package com.example.webbshop_backend1.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private String createErrorMessage(Throwable ex) {
        String message = String.format("Error from type= %s, occurred with error message=%s, result=%s", ex.getClass().getSimpleName(), ex.getMessage(), HttpStatus.NOT_FOUND);
        log.error(message);
        return message;
    }

    @ResponseStatus
    @ExceptionHandler(NotFoundCustomerException.class)
    protected ResponseEntity<Object> handleEmptyException(NotFoundCustomerException ex) {
        createErrorMessage(ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage());
    }

    @ResponseStatus
    @ExceptionHandler(NotSavedCustomerException.class)
    protected ResponseEntity<Object> handleNotSavedException(NotSavedCustomerException ex) {
        createErrorMessage(ex);
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage());
    }


}
