package com.ecommerce.loginmicroservice.requestDTO;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class LoginRequestDTO implements Serializable {
    private String username;
    private String password;
}