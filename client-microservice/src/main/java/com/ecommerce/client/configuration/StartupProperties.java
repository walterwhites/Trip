package com.ecommerce.client.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Configuration
@ConfigurationProperties(prefix = "client")
public class StartupProperties {

    private String username;
    private String fullName;
    private String password;
    private Character status;
    private String emailAddress;
    private Integer loginAttempt;
    private Long profileId;
}
