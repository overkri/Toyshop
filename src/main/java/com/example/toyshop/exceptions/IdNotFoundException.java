package com.example.toyshop.exceptions;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;


public class IdNotFoundException extends ResourceNotFoundException {

    public IdNotFoundException() {
        this("Product was not found for this id");
    }

    public IdNotFoundException(String message) {
        this(message, (Throwable)null);
    }

    public IdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
