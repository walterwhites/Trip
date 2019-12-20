package com.ecommerce.clientui.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity handleException(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(httpRequestMethodNotSupportedException.getMessage());
    }
}