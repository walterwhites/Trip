package com.ecommerce.zuulservermicroservice.Constants;

public class ErrorMessage {
    public interface TokenInvalid {
        String DEVELOPER_MESSAGE= "Request not authorized.";
        String MESSAGE ="Unmatched JWT token.";
    }
}
