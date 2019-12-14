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
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import com.ecommerce.zuulservermicroservice.constants.SecurityConstants.*;

import static com.ecommerce.zuulservermicroservice.constants.SecurityConstants.REFERER_HEADER;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private ClientInterface clientInterface;
    private HttpServletRequest request;

    public CustomUserDetailsService(ClientInterface clientInterface, HttpServletRequest request) {
        this.clientInterface = clientInterface;
        this.request = request;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientResponseDTO clientResponseDTO = clientInterface.fetchClientByUsername(username, this.request.getHeader(REFERER_HEADER))
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));

        List<GrantedAuthority> grantedAuthorities = clientResponseDTO.getRoles()
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(String.join("-", username, clientResponseDTO.getEmailAddress()),
                clientResponseDTO.getPassword(), true, true, true,
                true, grantedAuthorities);
    }
}