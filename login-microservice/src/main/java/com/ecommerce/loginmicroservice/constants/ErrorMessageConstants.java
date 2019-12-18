package com.ecommerce.loginmicroservice.constants;

public class ErrorMessageConstants {
    public interface ForgetPassword {
        String DEVELOPER_MESSAGE = "The password doesn't match with username / email";
    }

    public interface InvalidClientStatus {
        String DEVELOPER_MESSAGE_FOR_BLOCKED = "The client has status 'B'";
        String DEVELOPER_MESSAGE_FOR_INACTIVE = "The client has status 'N'";
        String MESSAGE_FOR_BLOCKED = "The client is in blocked state.";
        String MESSAGE_FOR_INACTIVE = "The client is in inactive state.";
    }

    public interface InvalidClientUsername {
        String DEVELOPER_MESSAGE = "Client with this username / email doesn't exits.";
    }

    public interface InvalidCredentials {
        String MESSAGE = "Username or password are incorrect";
    }

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
