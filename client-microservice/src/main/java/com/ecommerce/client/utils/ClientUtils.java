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
        final Integer FIRSTNAME = 1;
        final Integer LASTNAME = 2;
        final Integer PASSWORD = 3;
        final Integer STATUS = 4;
        final Integer LOGIN_ATTEMPT = 5;
        final Integer EMAIL_ADDRESS = 6;

        ClientResponseDTO responseDTO = new ClientResponseDTO();

        objects.forEach(object -> {
            responseDTO.setId(Long.parseLong(object[ID].toString()));
            responseDTO.setFirstname(object[FIRSTNAME].toString());
            responseDTO.setLastname(object[LASTNAME].toString());
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
