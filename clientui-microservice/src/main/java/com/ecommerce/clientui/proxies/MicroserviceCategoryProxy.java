package com.ecommerce.clientui.proxies;

import com.ecommerce.clientui.beans.CategoryBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import static com.ecommerce.clientui.constants.SecurityConstants.AUTHORIZATION_HEADER;


@FeignClient(name = "zuul-server-microservice")
@RibbonClient(name = "category-microservice")
public interface MicroserviceCategoryProxy {

    @GetMapping(value = "/category-microservice/categories/{id}")
    CategoryBean getCategoryById(@PathVariable("id") int id, @RequestHeader(value = AUTHORIZATION_HEADER) String authorisation);
}
