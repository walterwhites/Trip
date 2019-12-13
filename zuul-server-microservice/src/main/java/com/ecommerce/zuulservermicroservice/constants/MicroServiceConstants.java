package com.ecommerce.zuulservermicroservice.constants;

public class MicroServiceConstants {

    public static final String CLIENT_MICROSERVICE = "client-microservice";
    public static final String BASE_API = "/api";
    public static final String LOGIN_MICROSERVICE = "/login-microservice/api/login";
    public static final String ADVENTURE_MICROSERVICE = "/adventure-microservice/adventures";

    public interface ClientMicroServiceConstants {
        String FETCH_CLIENT_BY_USERNAME = "/fetch-client/{username}";
    }
}
