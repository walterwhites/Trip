package com.ecommerce.client.exceptions;

import org.springframework.http.HttpStatus;

public class DataDuplicationException extends RuntimeException {
    private ErrorResponse errorResponse;

    public DataDuplicationException(String message, String developerMessage) {
        super(message);

        errorResponse = new ErrorResponse();

        errorResponse.setDeveloperMsg(developerMessage);
        errorResponse.setErrorMsg(message);
        errorResponse.setStatus(HttpStatus.CONFLICT);
    }
}
