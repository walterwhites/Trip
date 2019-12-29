package com.ecommerce.comment.configuration;

import com.ecommerce.comment.utils.CookiesUtils;
import com.ecommerce.comment.utils.DebugUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

import static com.ecommerce.comment.constants.SecurityConstants.*;

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
        requestTemplate.header(REFERER_HEADER, request.getHeader(REFERER_HEADER));
        DebugUtils.RequestInfo.displayAllRequestHeaders(request);
    }
}