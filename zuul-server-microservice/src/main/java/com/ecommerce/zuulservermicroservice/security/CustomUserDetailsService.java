package com.ecommerce.zuulservermicroservice.security;

import com.ecommerce.zuulservermicroservice.ResponseDTO.ClientResponseDTO;
import com.ecommerce.zuulservermicroservice.feignInterface.ClientInterface;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private ClientInterface clientInterface;

    public CustomUserDetailsService(ClientInterface clientInterface) {
        this.clientInterface = clientInterface;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("debug94329" + clientInterface.fetchClientByUsername(username));
        ClientResponseDTO clientResponseDTO = clientInterface.fetchClientByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));

        List<GrantedAuthority> grantedAuthorities = clientResponseDTO.getRoles()
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(String.join("-", username, clientResponseDTO.getEmailAddress()),
                clientResponseDTO.getPassword(), true, true, true,
                true, grantedAuthorities);
    }
}