package com.ecommerce.payment.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "payment")
@PropertySource(value = "classpath:startup.properties", ignoreResourceNotFound=true)
public class StartupProperties {

    private String commandId;
    private int clientId;
    private int adventureId;
    private String state;
    private int amount;
}
