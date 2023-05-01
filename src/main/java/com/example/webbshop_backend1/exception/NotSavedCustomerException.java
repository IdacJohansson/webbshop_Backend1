package com.example.webbshop_backend1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class NotSavedCustomerException extends Throwable {

    public NotSavedCustomerException() {
    }

    public NotSavedCustomerException(String message) {
        super(message);
    }

    public NotSavedCustomerException(Throwable cause) {
        super(cause);
    }

    public NotSavedCustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
