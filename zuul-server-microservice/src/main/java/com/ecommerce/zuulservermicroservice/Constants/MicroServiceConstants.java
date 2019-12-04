package com.ecommerce.zuulservermicroservice.Constants;

public class MicroServiceConstants {

    public static final String LOGIN_MICROSERVICE = "/login-microservice/api/login";
    public static final String CLIENT_MICROSERVICE = "client-microservice";
    public static final String BASE_API = "/api";

    public interface ClientMicroServiceConstants {
        String FETCH_CLIENT_BY_USERNAME = "/fetch-client/{username}";
    }
}
