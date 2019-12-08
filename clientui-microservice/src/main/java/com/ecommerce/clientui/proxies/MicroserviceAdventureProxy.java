package com.ecommerce.clientui.proxies;

import com.ecommerce.clientui.beans.AdventureBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "zuul-server-microservice")
@RibbonClient(name = "adventure-microservice")
public interface MicroserviceAdventureProxy {

    @GetMapping(value = "/adventure-microservice/adventures")
    List<AdventureBean> adventureList();

    @GetMapping(value = "/adventure-microservice/adventures/{id}")
    AdventureBean displayAdventure(@PathVariable("id") int id);
}
