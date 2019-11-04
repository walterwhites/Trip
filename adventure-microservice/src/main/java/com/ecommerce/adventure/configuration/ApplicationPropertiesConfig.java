package com.ecommerce.adventure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("my-configs")
public class ApplicationPropertiesConfig {

    private int limitAdventures;

    public int getLimitAdventures() {
        return limitAdventures;
    }

    public void setLimitAdventures(int limitAdventures) {
        this.limitAdventures = limitAdventures;
    }
}
