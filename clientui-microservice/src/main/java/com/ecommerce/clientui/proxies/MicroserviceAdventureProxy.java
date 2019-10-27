package com.ecommerce.clientui.proxies;


import com.ecommerce.clientui.beans.AdventureBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "adventure-microservice", url = "localhost:9090")
public interface MicroserviceAdventureProxy {

    @GetMapping(value = "adventures")
    List<AdventureBean> adventureList();

    @GetMapping(value = "adventures/{id}")
    AdventureBean displayAdventure(@PathVariable("id") int id);
}
