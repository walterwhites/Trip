package com.ecommerce.clientui.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import static com.ecommerce.clientui.constants.ErrorMessage.DataDuplication.*;
import static com.ecommerce.clientui.constants.ErrorMessage.MissingField.*;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    private Response response;
    private String responseBody;

    @Override
    public Exception decode(String s, Response response) {

        this.response = response;

        if (response.body() != null) {
            this.responseBody = response.body().toString();
        }

        if (response.status() == 404 && s.contains("MicroserviceAdventureProxy")) {
            return new AdventureNotFoundException("Adventure not found");
        } else if (response.status() == 404 && s.contains("MicroserviceLoginProxy")) {
            return new ClientNotFoundException("Client not found");
        }

        if (response.status() == 403) {
            return new UnauthorisedException("Access denied", "Access denied, zuul server responds 403");
        }

        if (response.status() == 401) {
            return new UnauthorisedException("Access denied, not authentified", "Access denied, zuul server responds 401");
        }

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
