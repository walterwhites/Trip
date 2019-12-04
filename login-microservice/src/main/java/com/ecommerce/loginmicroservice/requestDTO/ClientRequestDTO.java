package com.ecommerce.loginmicroservice.requestDTO;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRequestDTO implements Serializable {
    private String username;
    private String emailAddress;
}
