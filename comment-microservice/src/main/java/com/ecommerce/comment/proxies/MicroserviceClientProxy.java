package com.ecommerce.comment.proxies;

import com.ecommerce.comment.constants.MicroServiceConstants;
import com.ecommerce.comment.responseDTO.ClientResponseDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static com.ecommerce.comment.constants.MicroServiceConstants.ClientMicroServiceConstants.*;
import static com.ecommerce.comment.constants.SecurityConstants.*;

@FeignClient(name ="zuul-server-microservice")
@RibbonClient(name = "client-microservice")
public interface MicroserviceClientProxy {

    @RequestMapping(value = SEARCH_CLIENT)
    ClientResponseDTO searchClient(@RequestHeader(value = REFERER_HEADER) String referer);

    @RequestMapping(value = BASE + MicroServiceConstants.BASE_API + FETCH_CLIENT_BY_USERNAME)
    Optional<ClientResponseDTO> fetchClientByUsername(@PathVariable("username") String username, @RequestHeader(value = REFERER_HEADER) String referer, @RequestHeader(value = AUTHORIZATION_HEADER) String authorisation);
}