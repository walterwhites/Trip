package com.ecommerce.clientui.requestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequestDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String emailAddress;
}
