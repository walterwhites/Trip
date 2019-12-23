package com.ecommerce.clientui.proxies;

import com.ecommerce.clientui.requestDTO.ChargeRequestDTO;
import com.ecommerce.clientui.requestDTO.PaymentDetailRequestDTO;
import com.ecommerce.clientui.requestDTO.RefundRequestDTO;
import com.ecommerce.clientui.responseDTO.ChargeResponseDTO;
import com.ecommerce.clientui.responseDTO.PaymentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import static com.ecommerce.clientui.constants.SecurityConstants.AUTHORIZATION_HEADER;
import static com.ecommerce.clientui.constants.SecurityConstants.REFERER_HEADER;

@FeignClient(name = "payment-microservice")
@Service
public interface MicroservicePaymentProxy {

    @PostMapping(value = "/charge")
    Optional<ChargeResponseDTO> charge(@RequestBody() ChargeRequestDTO chargeRequestDTO, @RequestHeader(value= REFERER_HEADER) String referer, @RequestHeader(value= AUTHORIZATION_HEADER) String authorisation);

    @PostMapping(value = "/payments")
    List<PaymentResponseDTO> payments(@RequestBody Long clientId, @RequestHeader(value= REFERER_HEADER) String referer, @RequestHeader(value= AUTHORIZATION_HEADER) String authorisation);

    @PostMapping(value = "/payment")
    Optional<PaymentResponseDTO> paymentDetail(@RequestBody() PaymentDetailRequestDTO paymentDetailRequestDTO, @RequestHeader(value= REFERER_HEADER) String referer, @RequestHeader(value= AUTHORIZATION_HEADER) String authorisation);

    @PostMapping(value = "/refund")
    void refundCommand(@RequestBody() RefundRequestDTO refundRequestDTO, @RequestHeader(value= REFERER_HEADER) String referer, @RequestHeader(value= AUTHORIZATION_HEADER) String authorisation);
}