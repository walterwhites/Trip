package com.ecommerce.clientui.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {

        if (response.status() == 404 && s.contains("MicroserviceAdventureProxy")) {
            return new AdventureNotFoundException("Adventure not found");
        } else if (response.status() == 404 && s.contains("MicroserviceLoginProxy")) {
            return new ClientNotFoundException("Client not found");
        }

        if (response.status() == 403) {
            return new UnauthorisedException("Access denied", "Access denied, zuul server responds 403");
        }

        return defaultErrorDecoder.decode(s, response);
    }
}
