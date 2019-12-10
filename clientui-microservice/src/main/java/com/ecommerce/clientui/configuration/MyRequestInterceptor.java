package com.ecommerce.clientui.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.ecommerce.clientui.constants.SecurityConstants.REFERER_HEADER;
import static com.ecommerce.clientui.constants.SecurityConstants.REFERER_HEADER_VALUE;

@Component
public class MyRequestInterceptor implements RequestInterceptor {

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

        /*String refererHeader = request.getHeader(REFERER);
        if (refererHeader == null) {
            return;
        }*/
        requestTemplate.header(REFERER_HEADER, REFERER_HEADER_VALUE);
    }
}