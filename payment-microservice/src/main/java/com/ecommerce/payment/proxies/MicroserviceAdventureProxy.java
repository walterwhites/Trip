package com.ecommerce.payment.proxies;

import com.ecommerce.payment.beans.AdventureBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ecommerce.payment.constants.SecurityConstants.AUTHORIZATION_HEADER;
import static com.ecommerce.payment.constants.SecurityConstants.REFERER_HEADER;

@FeignClient(name = "zuul-server-microservice")
@RibbonClient(name = "adventure-microservice")
public interface MicroserviceAdventureProxy {

    @GetMapping(value = "/adventure-microservice/adventures")
    List<AdventureBean> adventureList(@RequestHeader(value = AUTHORIZATION_HEADER) String authorisation);

    @GetMapping(value = "/adventure-microservice/adventures/{id}")
    AdventureBean displayAdventure(@PathVariable("id") int id, @RequestHeader(value = AUTHORIZATION_HEADER) String authorisation);

    @PostMapping(value = "/adventure-microservice/adventures/entrants/reduce")
    void reduceEntrantsAdventure(@RequestBody() String chargeId, @RequestBody() String adventure, @RequestHeader(value = REFERER_HEADER) String referer, @RequestHeader(value = AUTHORIZATION_HEADER) String authorisation);

    @PostMapping(value = "/adventure-microservice/adventures/entrants/up")
    void upEntrantsAdventure(@RequestBody() String chargeId, @RequestBody() String adventure, @RequestHeader(value = REFERER_HEADER) String referer, @RequestHeader(value = AUTHORIZATION_HEADER) String authorisation);
}
