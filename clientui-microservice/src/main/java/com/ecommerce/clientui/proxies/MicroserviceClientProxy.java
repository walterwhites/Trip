package com.ecommerce.clientui.proxies;

import com.ecommerce.clientui.constants.MicroServiceConstants.*;
import com.ecommerce.clientui.responseDTO.ClientResponseDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static com.ecommerce.clientui.constants.SecurityConstants.REFERER_HEADER;

@FeignClient(name ="zuul-server-microservice")
@RibbonClient(name = "client-microservice")
public interface MicroserviceClientProxy {

    @RequestMapping(value = ClientMicroServiceConstants.SEARCH_CLIENT)
    ClientResponseDTO searchClient(@RequestHeader(value= REFERER_HEADER) String referer);

    @RequestMapping(value = ClientMicroServiceConstants.FETCH_CLIENT_BY_USERNAME)
    Optional<ClientResponseDTO> fetchClientByUsername(@PathVariable("username") String username, @RequestHeader(value= REFERER_HEADER) String referer);
}
