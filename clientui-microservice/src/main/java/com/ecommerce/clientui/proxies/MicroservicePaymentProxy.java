package com.ecommerce.clientui.proxies;

import com.ecommerce.clientui.requestDTO.ChargeRequestDTO;
import com.ecommerce.clientui.responseDTO.ChargeResponseDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import static com.ecommerce.clientui.constants.SecurityConstants.AUTHORIZATION_HEADER;
import static com.ecommerce.clientui.constants.SecurityConstants.REFERER_HEADER;

@FeignClient(name = "payment-microservice")
@Service
public interface MicroservicePaymentProxy {

    @PostMapping(value = "/charge")
    Optional<ChargeResponseDTO> charge(@RequestBody() ChargeRequestDTO chargeRequestDTO, @RequestHeader(value= REFERER_HEADER) String referer, @RequestHeader(value= AUTHORIZATION_HEADER) String authorisation);
}