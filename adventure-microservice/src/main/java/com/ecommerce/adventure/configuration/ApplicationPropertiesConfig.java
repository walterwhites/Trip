package com.ecommerce.adventure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("my-configs")
@PropertySource("classpath:security.properties")
@RefreshScope
public class ApplicationPropertiesConfig {

    private int limitAdventures;

    public int getLimitAdventures() {
        return limitAdventures;
    }

    public void setLimitAdventures(int limitAdventures) {
        this.limitAdventures = limitAdventures;
    }
}
