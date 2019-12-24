package com.ecommerce.clientui.exception;

import com.ecommerce.clientui.constants.ErrorTemplate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MethodNotSupportedException extends CustomException {

    public MethodNotSupportedException(String message, String developerMessage) {
        super(message);

        errorResponse = new ErrorResponse();

        errorResponse.setDeveloperMsg(developerMessage);
        errorResponse.setErrorMsg(message);
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setTemplate(ErrorTemplate.BAD_REQUEST);
    }
}
