package com.ecommerce.client.responseDTO;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO implements Serializable {

    private List<ClientResponseDTO> adminResponseDTOS;
    private String message;
}