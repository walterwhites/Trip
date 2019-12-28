package com.ecommerce.comment.configuration;

import com.ecommerce.comment.filter.CustomGatewayFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.session.ConcurrentSessionFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterAfter(new CustomGatewayFilter(), ConcurrentSessionFilter.class)
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
                .headers().frameOptions().disable();

        //.antMatchers("/**").permitAll().and().headers().frameOptions().sameOrigin();
    }
}