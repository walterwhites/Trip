package com.ecommerce.zuulservermicroservice.feignInterface;

import com.ecommerce.zuulservermicroservice.ResponseDTO.ClientResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static com.ecommerce.zuulservermicroservice.Constants.MicroServiceConstants.*;

@FeignClient(name = CLIENT_MICROSERVICE)
@Service
@RequestMapping(value = BASE_API)
public interface ClientInterface {
    @RequestMapping(value = ClientMicroServiceConstants.FETCH_CLIENT_BY_USERNAME)
    Optional<ClientResponseDTO> fetchClientByUsername(@PathVariable("username") String username);
}
