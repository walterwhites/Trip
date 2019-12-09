package com.ecommerce.client.responseDTO;

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

    private String emailAddress;
    private Long id;
    private String password;
    private Character status;
    private Integer loginAttempt;
}
