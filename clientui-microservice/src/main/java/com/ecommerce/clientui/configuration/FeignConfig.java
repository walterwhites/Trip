package com.ecommerce.clientui.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor(){
        return  new BasicAuthRequestInterceptor("clientui-microservice",
                "9234kfj-bZKSI-FIF93IF-JSFJ-S8-jfsh-jfhsOQ93");
    }

}