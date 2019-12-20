package com.ecommerce.payment.filter;

import com.ecommerce.payment.exceptions.ErrorResponse;
import com.ecommerce.payment.exceptions.UnauthorisedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.ecommerce.payment.utils.DebugUtils.*;

import static com.ecommerce.payment.constants.SecurityConstants.*;


public class CustomGatewayFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        ZipkinDebug.displayTraceUrl(request);
        RequestInfo.displayAllRequestHeaders(request);

        if ((RequestInfo.getRequestHeader(request, REFERER_HEADER) == null) &&
                !request.getRequestURL().toString().contains("localhost")) {

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