package com.ecommerce.trip.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdventureNotFoundException extends RuntimeException {
    public AdventureNotFoundException(String s) {
        super(s);
    }
}
