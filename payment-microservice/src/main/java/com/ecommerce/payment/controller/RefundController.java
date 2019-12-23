package com.ecommerce.payment.controller;

import brave.http.HttpServerRequest;
import com.ecommerce.payment.exceptions.UnauthorisedException;
import com.ecommerce.payment.requestDTO.PaymentDetailRequestDTO;
import com.ecommerce.payment.requestDTO.RefundRequestDTO;
import com.ecommerce.payment.responseDTO.ClientResponseDTO;
import com.ecommerce.payment.responseDTO.PaymentResponseDTO;
import com.ecommerce.payment.service.impl.ClientServiceImpl;
import com.ecommerce.payment.service.impl.PaymentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.ecommerce.payment.constants.SecurityConstants.AUTHORIZATION_HEADER;
import static com.ecommerce.payment.constants.SecurityConstants.REFERER_HEADER;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping()
@Api(value = "This is refund controller")
public class RefundController {

    private final PaymentServiceImpl paymentservice;
    private final ClientServiceImpl clientService;

    public RefundController(PaymentServiceImpl paymentservice, ClientServiceImpl clientService) {
        this.paymentservice = paymentservice;
        this.clientService = clientService;
    }

    @PostMapping(value="refund")
    @ApiOperation(value = "Refund a command")
    @ResponseBody
    ResponseEntity<?> refundCommand(@RequestBody RefundRequestDTO refundRequestDto, @RequestHeader(value= REFERER_HEADER) String referer, @RequestHeader(value= AUTHORIZATION_HEADER) String authorisation, HttpServerRequest request) {
        try {
            Optional<ClientResponseDTO> clientResponseDTO = clientService.getUserInformations();
            if (!refundRequestDto.getClientId().equals(clientResponseDTO.get().getId())) {
                throw new UnauthorisedException("Wrong user", "A client try to refund a other client's command");
            }
            paymentservice.refundCard(refundRequestDto.getChargeId());
            return ok().build();
        } catch (Exception exception) {
            return badRequest().body(exception.getMessage());
        }
    }
}
