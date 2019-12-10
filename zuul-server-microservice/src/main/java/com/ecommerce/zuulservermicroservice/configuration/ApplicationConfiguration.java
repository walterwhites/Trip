package com.ecommerce.zuulservermicroservice.configuration;

import com.ecommerce.zuulservermicroservice.filters.MyZuulFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:security.properties")
public class ApplicationConfiguration {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}