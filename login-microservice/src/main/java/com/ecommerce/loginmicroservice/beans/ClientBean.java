package com.ecommerce.loginmicroservice.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClientBean implements Serializable {

    private Long id;
    private String username;
    private String fullName;
    private String emailAddress;
    private Long profileId;
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
