package com.example.webbshop_backend1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundCustomerException extends Throwable {

    public NotFoundCustomerException() {
        super();
    }

    public NotFoundCustomerException(String message, Throwable cause) {
        super(message, cause);    }

    public NotFoundCustomerException(String message) {
        super(message);
    }

    public NotFoundCustomerException(Throwable cause) {
        super(cause);
    }
}
