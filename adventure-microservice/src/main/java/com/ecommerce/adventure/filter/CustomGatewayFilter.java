package com.ecommerce.adventure.filter;
import com.ecommerce.adventure.constants.GatewayConstant;
import com.ecommerce.adventure.exception.ErrorResponse;
import com.ecommerce.adventure.exception.UnauthorisedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomGatewayFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String proxyForwardedHostHeader = request.getHeader("X-Forwarded-Host");

        if (proxyForwardedHostHeader == null || !proxyForwardedHostHeader.equals(GatewayConstant.getGatewayURL())) {
            UnauthorisedException unauthorisedException = new UnauthorisedException("Unauthorized Access",
                    "Unauthorized Access, you should pass through the API gateway");
            byte[] responseToSend = restResponseBytes(unauthorisedException.getErrorResponse());
            ((HttpServletResponse) response).setHeader("Content-Type", "application/json");
            ((HttpServletResponse) response).setStatus(401);
            response.getOutputStream().write(responseToSend);
            return;
        }
        chain.doFilter(request, response);
    }

    private byte[] restResponseBytes(ErrorResponse errorResponse) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(errorResponse);
        return serialized.getBytes();
    }
}