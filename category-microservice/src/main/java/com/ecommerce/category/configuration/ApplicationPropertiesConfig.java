package com.ecommerce.category.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("my-configs")
@RefreshScope
public class ApplicationPropertiesConfig {

}
