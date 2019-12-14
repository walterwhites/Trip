package com.ecommerce.client.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Configuration
@Component
@ConfigurationProperties(prefix = "client")
@PropertySource("classpath:startup.properties")
public class StartupProperties {

    private String username;
    private String firstname;
    private String lastname;
    private String fullName;
    private String password;
    private Character status;
    private String emailAddress;
    private Integer loginAttempt;
    private Long profileId;
}