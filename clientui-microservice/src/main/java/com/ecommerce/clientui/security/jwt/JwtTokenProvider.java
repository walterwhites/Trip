package com.ecommerce.clientui.security.jwt;

import com.ecommerce.clientui.constants.ErrorMessage;
import com.ecommerce.clientui.exception.UnauthorisedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.Date;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Component
public class JwtTokenProvider {

    private String secretKey;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            return (!claims.getBody().getExpiration().before(new Date()));
        } catch (JwtException | IllegalArgumentException e) {
            LOGGER.error("Expired or invalid JWT token");
            throw new UnauthorisedException(ErrorMessage.TokenInvalid.MESSAGE, ErrorMessage.TokenInvalid.DEVELOPER_MESSAGE);
        }
    }
}
