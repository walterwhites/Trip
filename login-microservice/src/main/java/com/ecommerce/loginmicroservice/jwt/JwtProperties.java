package com.ecommerce.loginmicroservice.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@ConfigurationProperties(prefix = "jwt")
@Data
@Configuration
public class JwtProperties {
    private String secretKey;
    private long validityInMilliseconds;
}