package com.ecommerce.zuulservermicroservice.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import static com.ecommerce.zuulservermicroservice.constants.SecurityConstants.REFERER_HEADER;
import static com.ecommerce.zuulservermicroservice.constants.SecurityConstants.ZUUL_SERVER_MICROSERVICE_REFERER;

@Component
public class MyZuulFilter extends ZuulFilter {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        log.info("request intercepted " + request.getRequestURL());

        RequestContext ctx = RequestContext.getCurrentContext();
        if(SecurityContextHolder.getContext() != null) {
            ctx.addZuulRequestHeader(REFERER_HEADER, ZUUL_SERVER_MICROSERVICE_REFERER);
        }

        return null;
    }
}
