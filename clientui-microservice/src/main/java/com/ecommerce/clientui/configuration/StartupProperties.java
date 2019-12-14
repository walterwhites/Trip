package com.ecommerce.clientui.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("client")
@RefreshScope
@PropertySource("classpath:startup.properties")
public class StartupProperties {

    private String username;
    private String fullName;
    private String password;
    private Character status;
    private String emailAddress;
    private Integer loginAttempt;
    private Long profileId;
}
