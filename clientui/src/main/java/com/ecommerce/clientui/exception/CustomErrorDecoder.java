package com.ecommerce.clientui.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {

        if (response.status() == 404) {
            return new AdventureNotFoundException("Adventure not found");
        }

        return defaultErrorDecoder.decode(s, response);
    }
}
