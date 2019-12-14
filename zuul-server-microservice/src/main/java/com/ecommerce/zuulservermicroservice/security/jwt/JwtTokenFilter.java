package com.ecommerce.zuulservermicroservice.security.jwt;

import com.ecommerce.zuulservermicroservice.exceptionHandler.ErrorResponse;
import com.ecommerce.zuulservermicroservice.exceptionHandler.UnauthorisedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);

        try {
            if (!Objects.isNull(token) && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                if (!Objects.isNull(auth))
                    SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (UnauthorisedException unauthorisedException) {
            ((HttpServletResponse) response).setStatus(unauthorisedException.getErrorResponse().getStatus().value());
            return;
        }

        filterChain.doFilter(request, response);
    }

}
