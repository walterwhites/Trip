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
}