package com.ecommerce.zuulservermicroservice.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyRequestInterceptor implements RequestInterceptor {
    private static final String REFERER_HEADER = "TripApplication//microservice::zuul-server-microservice";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        if (request == null) {
            return;
        }
        String xForwardedForHeader = request.getHeader(REFERER_HEADER);
        if (xForwardedForHeader == null) {
            return;
        }
        requestTemplate.header(REFERER_HEADER, xForwardedForHeader);
    }
}