package com.ecommerce.loginmicroservice.controller;

import com.ecommerce.loginmicroservice.exceptionHandler.DataDuplicationException;
import com.ecommerce.loginmicroservice.exceptionHandler.MissingFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(DataDuplicationException.class)
    public ResponseEntity handleException(DataDuplicationException dataDuplicationException) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(dataDuplicationException.getErrorResponse().getErrorMsg());
    }

    @ExceptionHandler(MissingFieldException.class)
    public ResponseEntity handleMissingFieldException(MissingFieldException missingFieldException) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(missingFieldException.getErrorResponse().getErrorMsg());
    }
}