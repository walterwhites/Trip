package com.ecommerce.clientui.controller;

import com.ecommerce.clientui.constants.ErrorTemplate;
import com.ecommerce.clientui.exception.UnauthorisedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler(UnauthorisedException.class)
    public String handleUnauthorisedException(UnauthorisedException unauthorisedException) {
        logger.info("LoggerMessage: " + unauthorisedException.getMessage());
        return ErrorTemplate.UNAUTHORIZED;
    }

    @ExceptionHandler(Exception.class)
    public String handleBadRequestException(Exception exception) {
        logger.info("LoggerMessage :" +  exception.getMessage());
        return ErrorTemplate.BAD_REQUEST;
    } // HttpRequestMethodNotSupportedException.class, BadRequestException.class
}