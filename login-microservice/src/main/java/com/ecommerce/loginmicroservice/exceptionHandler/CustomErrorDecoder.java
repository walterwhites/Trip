package com.ecommerce.loginmicroservice.exceptionHandler;

import feign.Response;
import feign.codec.ErrorDecoder;
import static com.ecommerce.loginmicroservice.constants.ErrorMessageConstants.DataDuplication.*;
import static com.ecommerce.loginmicroservice.constants.ErrorMessageConstants.MissingField.*;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    private Response response;
    private String responseBody;

    @Override
    public Exception decode(String s, Response response) {

        this.response = response;
        this.responseBody = response.body().toString();

        Exception checkDataDuplicationExceptionUsername = checkDataDuplicationException(DUPLICATE_USERNAME_MESSAGE, DUPLICATE_USERNAME_DEVELOPER_MESSAGE);
        if (checkDataDuplicationExceptionUsername != null) return checkDataDuplicationExceptionUsername;

        Exception checkDataDuplicationExceptionEmail = checkDataDuplicationException(DUPLICATE_EMAILADDRESS_MESSAGE, DUPLICATE_EMAILADDRESS_DEVELOPER_MESSAGE);
        if (checkDataDuplicationExceptionEmail != null) return checkDataDuplicationExceptionEmail;

        Exception checkMissingFieldExceptionEmail = checkMissingFieldException(MISSING_EMAIL_MESSAGE);
        if (checkMissingFieldExceptionEmail != null) return checkMissingFieldExceptionEmail;

        Exception checkMissingFieldExceptionUsername = checkMissingFieldException(MISSING_USERNAME_MESSAGE);
        if (checkMissingFieldExceptionUsername != null) return checkMissingFieldExceptionUsername;

        Exception checkMissingFieldExceptionFirstname = checkMissingFieldException(MISSING_FIRSTNAME_MESSAGE);
        if (checkMissingFieldExceptionFirstname != null) return checkMissingFieldExceptionFirstname;

        Exception checkMissingFieldExceptionLastname = checkMissingFieldException(MISSING_LASTNAME_MESSAGE);
        if (checkMissingFieldExceptionLastname != null) return checkMissingFieldExceptionLastname;

        Exception checkMissingFieldExceptionPassword = checkMissingFieldException(MISSING_PASSWORD_MESSAGE);
        if (checkMissingFieldExceptionPassword != null) return checkMissingFieldExceptionPassword;

        return defaultErrorDecoder.decode(s, response);
    }

    private DataDuplicationException checkDataDuplicationException(String message, String devMessage) {
        if (response.status() == 409 && responseBody.contains(message)) {
            return new DataDuplicationException(message, devMessage);
        }
        return null;
    }

    private MissingFieldException checkMissingFieldException(String message) {
        if (response.status() == 422 && responseBody.contains(message)) {
            return new MissingFieldException(message, message);
        }
        return null;
    }
}
