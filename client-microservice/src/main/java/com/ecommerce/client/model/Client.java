package com.ecommerce.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "client")
@Entity
@Getter
@Setter
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "email_address", length = 200)
    private String emailAddress;

    @Column(name = "profile_id")
    private Long profileId;

    @Column(name="password")
    private String password;

    @Column(name="status")
    private Character status;

    @Column(name="login_attempt")
    private Integer loginAttempt;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Column(name = "email_sent")
    private Character emailSent;
}
