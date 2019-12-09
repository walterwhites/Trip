package com.ecommerce.client.filter;
import com.ecommerce.client.exceptions.ErrorResponse;
import com.ecommerce.client.exceptions.UnauthorisedException;
import com.ecommerce.client.utils.DebugUtils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.ecommerce.client.constants.SecurityConstants.LOGIN_MICROSERVICE_REFERER;
import static com.ecommerce.client.constants.SecurityConstants.REFERER_HEADER;

public class CustomGatewayFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        ZipkinDebug.displayTraceUrl(request);
        RequestInfo.displayAllRequestHeaders(request);

        if (RequestInfo.getRequestHeader(request, REFERER_HEADER) == null ||
                !RequestInfo.getRequestHeader(request, REFERER_HEADER).equals(LOGIN_MICROSERVICE_REFERER)) {

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