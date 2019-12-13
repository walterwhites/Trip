package com.ecommerce.zuulservermicroservice.configuration;

import com.ecommerce.zuulservermicroservice.security.jwt.JwtConfigurer;
import com.ecommerce.zuulservermicroservice.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static com.ecommerce.zuulservermicroservice.constants.MicroServiceConstants.*;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_MICROSERVICE).permitAll()
                .anyRequest().authenticated()
                .and().antMatcher(ADVENTURE_MICROSERVICE)
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
