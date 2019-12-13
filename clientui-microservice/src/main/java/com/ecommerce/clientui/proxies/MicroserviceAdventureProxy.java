package com.ecommerce.clientui.proxies;

import com.ecommerce.clientui.beans.AdventureBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

import static com.ecommerce.clientui.constants.SecurityConstants.AUTHORIZATION_HEADER;

@FeignClient(name = "zuul-server-microservice")
@RibbonClient(name = "adventure-microservice")
public interface MicroserviceAdventureProxy {

    @GetMapping(value = "/adventure-microservice/adventures")
    List<AdventureBean> adventureList(@RequestHeader(value= AUTHORIZATION_HEADER) String authorisation);

    @GetMapping(value = "/adventure-microservice/adventures/{id}")
    AdventureBean displayAdventure(@PathVariable("id") int id, @RequestHeader(value= AUTHORIZATION_HEADER) String authorisation);
}
