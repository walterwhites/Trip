package com.ecommerce.client.utils;

import com.ecommerce.client.responseDTO.ClientResponseDTO;

import java.util.List;
import java.util.function.Function;

public class ClientUtils {

    public static Function<Object[], ClientResponseDTO> convertToResponse = (object) -> {
        final Integer CLIENT_ID = 0;
        final Integer EMAIL_ADDRESS_INDEX = 1;

        return ClientResponseDTO.builder()
                .id(Long.parseLong(object[CLIENT_ID].toString()))
                .emailAddress(object[EMAIL_ADDRESS_INDEX].toString())
                .build();
    };

    public static Function<List<Object[]>, ClientResponseDTO> convertToClientResponse = (objects) -> {
        final Integer ID = 0;
        final Integer PASSWORD = 1;
        final Integer STATUS = 2;
        final Integer LOGIN_ATTEMPT = 3;
        final Integer EMAIL_ADDRESS = 4;

        ClientResponseDTO responseDTO = new ClientResponseDTO();

        objects.forEach(object -> {
            responseDTO.setId(Long.parseLong(object[ID].toString()));
            responseDTO.setPassword(object[PASSWORD].toString());
            responseDTO.setStatus(object[STATUS].toString().charAt(0));
            if (object[LOGIN_ATTEMPT] == null) {
                responseDTO.setLoginAttempt(null);
            } else {
                responseDTO.setLoginAttempt(Integer.parseInt(object[LOGIN_ATTEMPT].toString()));
            }
            responseDTO.setEmailAddress(object[EMAIL_ADDRESS].toString());
        });

        return responseDTO;
    };
}
