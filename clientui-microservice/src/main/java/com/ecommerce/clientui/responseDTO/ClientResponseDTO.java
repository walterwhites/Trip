package com.ecommerce.clientui.responseDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ClientResponseDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String emailAddress;
    private String password;
    private Character status;
    private Integer loginAttempt;
    private Date createdDate;
    private Date lastModifiedDate;
    private List<String> roles = new ArrayList<>();
}