package com.ecommerce.client.constants;

public class ErrorMessage {
    public interface DataDuplication {
        String DUPLICATE_USERNAME_MESSAGE = "Username already taken";
        String DUPLICATE_EMAILADDRESS_MESSAGE = "Email address already taken";
        String DUPLICATE_USERNAME_DEVELOPER_MESSAGE = "User try to register with an existing username";
        String DUPLICATE_EMAILADDRESS_DEVELOPER_MESSAGE = "User try to register with an existing email";
    }
}
