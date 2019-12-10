package com.ecommerce.clientui.proxies;

import com.ecommerce.clientui.beans.ClientBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import static com.ecommerce.clientui.constants.SecurityConstants.*;

@FeignClient(name ="zuul-server-microservice")
@RibbonClient(name = "login-microservice")
public interface MicroserviceLoginProxy {

    @PostMapping(value = "login-microservice/api/login")
    ClientBean postLogin(@RequestBody() ClientBean clientBean, @RequestHeader(value= REFERER_HEADER) String referer);
}
