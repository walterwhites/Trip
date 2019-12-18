package com.ecommerce.clientui.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClientBean implements Serializable {

    private Long id;

    @Size(min = 2, max = 30, message = "Username must be between 2 and 30 characters")
    private String username;

    @Size(min = 2, max = 30, message = "Password must be between 2 and 30 characters")
    private String firstname;

    @Size(min = 2, max = 30, message = "Password must be between 2 and 30 characters")
    private String lastname;

    private String fullName;

    @Size(min = 6, max = 80, message = "Email must be between 6 and 80 characters")
    @Email(message = "Email should be valid")
    private String emailAddress;

    private Long profileId;

    @Size(min = 4, max = 30, message = "Password must be between 4 and 30 characters")
    private String password;

    private Character status;

    private Integer loginAttempt;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();
    private Character emailSent;

    @Override
    public String toString() {
        return "ClientBean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", profileId=" + profileId +
                '}';
    }
}
