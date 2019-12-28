package com.ecommerce.comment.constants;

public class MicroServiceConstants {
    public static final String BASE_API = "/api";

    public interface ClientMicroServiceConstants {
        String BASE = "client-microservice";
        String SEARCH_CLIENT = "/search";
        String UPDATE_CLIENT = "/update";
        String FETCH_CLIENT_BY_USERNAME = "/fetch-client/{username}";
    }
}
