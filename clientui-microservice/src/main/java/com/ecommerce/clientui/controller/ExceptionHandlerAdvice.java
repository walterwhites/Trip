package com.ecommerce.clientui.controller;

import com.ecommerce.clientui.constants.ErrorTemplate;
import com.ecommerce.clientui.exception.UnauthorisedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(UnauthorisedException.class)
    public String handleUnauthorisedException() {
        return ErrorTemplate.UNAUTHORIZED;
    }

    @ExceptionHandler(Exception.class)
    public String handleBadRequestException() {
        return ErrorTemplate.BAD_REQUEST;
    } // HttpRequestMethodNotSupportedException.class, BadRequestException.class
}