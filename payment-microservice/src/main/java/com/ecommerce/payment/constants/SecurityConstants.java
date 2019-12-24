package com.ecommerce.payment.constants;

public class SecurityConstants {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    public static final String REFERER_HEADER = "referer";
    public static final String REFERER_HEADER_VALUE = "TripApplication//microservice::clientui-microservice";
    public static final String ZUUL_SERVER_MICROSERVICE_REFERER = "TripApplication//microservice::zuul-server-microservice";

    public static final String JWT_COOKIE = "jwt_token";
}
