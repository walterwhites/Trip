package com.ecommerce.client.constants;

public class ErrorMessage {
    public interface DataDuplication {
        String DUPLICATE_USERNAME_MESSAGE = "Username already taken";
        String DUPLICATE_EMAILADDRESS_MESSAGE = "Email address already taken";
        String DUPLICATE_USERNAME_DEVELOPER_MESSAGE = "User try to register with an existing username";
        String DUPLICATE_EMAILADDRESS_DEVELOPER_MESSAGE = "User try to register with an existing email";
    }

    public interface MissingField {
        String MISSING_EMAIL_MESSAGE = "emailAddress is missing";
        String MISSING_PASSWORD_MESSAGE = "password is missing";
        String MISSING_USERNAME_MESSAGE = "username is missing";
        String MISSING_FIRSTNAME_MESSAGE = "firstname is missing";
        String MISSING_LASTNAME_MESSAGE = "lastname is missing";
    }
}
