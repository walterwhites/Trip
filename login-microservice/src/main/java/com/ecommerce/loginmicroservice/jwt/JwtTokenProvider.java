package com.ecommerce.loginmicroservice.jwt;

import com.ecommerce.loginmicroservice.responseDTO.NetworkResponseDTO;
import com.ecommerce.loginmicroservice.utils.NetworkUtils;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

import static com.ecommerce.loginmicroservice.Constants.JWTConstants.JWT_KEY;

@Component
public class JwtTokenProvider {

    @Autowired
    JwtProperties jwtProperties;

    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
    }

    public String createToken(String username, HttpServletRequest request) {
        NetworkResponseDTO network = NetworkUtils.getDeviceAddresses.apply(request);

        return Jwts.builder()
                .setSubject(username)
                .claim("mac", network.getMacAddress())
                .claim("ip", network.getIpAddress())
                .setIssuer(JWT_KEY)
                .setExpiration(calculateExpirationDate())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private Date calculateExpirationDate() {
        Date now = new Date();
        return new Date(now.getTime() + jwtProperties.getValidityInMilliseconds());
    }
}