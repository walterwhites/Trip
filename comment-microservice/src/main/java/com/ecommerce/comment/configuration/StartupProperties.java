package com.ecommerce.comment.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "comment")
@PropertySource(value = "classpath:startup.properties", ignoreResourceNotFound=true)
public class StartupProperties {

    private int adventureId;
    private Long clientId;
    private String content;
}
