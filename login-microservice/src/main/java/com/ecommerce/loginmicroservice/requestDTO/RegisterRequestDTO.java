package com.ecommerce.loginmicroservice.requestDTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RegisterRequestDTO implements Serializable {
    private String username;
    private String firstname;
    private String lastname;
    private String emailAddress;
    private String password;
    private Date createdDate;
    private Date lastModifiedDate;
    private Character status;
    private List<String> roles = new ArrayList<>();
}