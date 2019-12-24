package com.ecommerce.clientui.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MissingFieldException extends CustomException {

    public MissingFieldException(String message, String developerMessage) {
        super(message);

        errorResponse = new ErrorResponse();

        errorResponse.setDeveloperMsg(developerMessage);
        errorResponse.setErrorMsg(message);
        errorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
