package com.ecommerce.loginmicroservice.exceptionHandler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NotFoundException extends RuntimeException {
    private ErrorResponse errorResponse;

    public NotFoundException(String message, String developerMessage) {
        super(message);

        errorResponse = new ErrorResponse();

        errorResponse.setDeveloperMsg(developerMessage);
        errorResponse.setErrorMsg(message);
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
    }
}