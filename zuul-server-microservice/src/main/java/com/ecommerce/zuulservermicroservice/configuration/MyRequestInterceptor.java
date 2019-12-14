package com.ecommerce.zuulservermicroservice.configuration;

import com.ecommerce.zuulservermicroservice.utils.DebugUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import static com.ecommerce.zuulservermicroservice.constants.SecurityConstants.*;

@Component
public class MyRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = requestAttributes.getRequest();
        DebugUtils.RequestInfo.displayAllRequestHeaders(request);
        requestTemplate.header(REFERER_HEADER, ZUUL_SERVER_MICROSERVICE_REFERER);
    }
}