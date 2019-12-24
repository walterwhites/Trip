package com.ecommerce.payment.responseDTO;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO implements Serializable {

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
