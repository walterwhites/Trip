package com.ecommerce.client.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "This is client request class")
public class ClientRequestDTO implements Serializable {

    @ApiModelProperty(value = "This is the id of the client")
    private Long id;

    @ApiModelProperty(value = "This is the username of the client")
    private String username;

    @ApiModelProperty(value = "This is full name of the client")
    private String fullName;

    @ApiModelProperty(value = "This is the email address of the client")
    private String emailAddress;

    @ApiModelProperty(value = "This is the status of the client")
    private Character status;

    @ApiModelProperty(value = "This represents the profile and its respective user menus" +
            " that this client has access to")
    private Long profileId;

    @ApiModelProperty(value = "This checks if email has been sent")
    private Character emailSent;

    @ApiModelProperty(value = "This is the password of the client")
    private String password;
}

