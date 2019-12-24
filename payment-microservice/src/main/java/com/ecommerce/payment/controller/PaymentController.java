package com.ecommerce.payment.controller;

import com.ecommerce.payment.exceptions.UnauthorisedException;
import com.ecommerce.payment.requestDTO.PaymentDetailRequestDTO;
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
@Api(value = "This is payment controller")
public class PaymentController {

    private final PaymentServiceImpl paymentservice;
    private final ClientServiceImpl clientService;

    public PaymentController(PaymentServiceImpl paymentservice, ClientServiceImpl clientService) {
        this.paymentservice = paymentservice;
        this.clientService = clientService;
    }

    @PostMapping(value="payments")
    @ApiOperation(value = "Get payment of current client")
    @ResponseBody
    ResponseEntity<?> payments(@RequestBody Long clientId, @RequestHeader(value= REFERER_HEADER) String referer, @RequestHeader(value= AUTHORIZATION_HEADER) String authorisation) {
        try {
            Optional<ClientResponseDTO> clientResponseDTO = clientService.getUserInformations();
            if (!clientId.equals(clientResponseDTO.get().getId())) {
                throw new UnauthorisedException("Wrong user", "A client try to see list of a other client's command");
            }
            List<PaymentResponseDTO> payments = paymentservice.getPayments(clientId);
            return ok().body(payments);
        } catch (Exception exception) {
            return badRequest().body(exception.getMessage());
        }
    }

    @PostMapping(value="payment")
    @ApiOperation(value = "Get payment detail of current client")
    @ResponseBody
    ResponseEntity<?> payment(@RequestBody PaymentDetailRequestDTO paymentDetailRequestDTO, @RequestHeader(value= REFERER_HEADER) String referer, @RequestHeader(value= AUTHORIZATION_HEADER) String authorisation) {
        try {
            Optional<ClientResponseDTO> clientResponseDTO = clientService.getUserInformations();
            if (!paymentDetailRequestDTO.getClientId().equals(clientResponseDTO.get().getId())) {
                throw new UnauthorisedException("Wrong user", "A client try to see a other client's command");
            }
            PaymentResponseDTO payment = paymentservice.getPayment(paymentDetailRequestDTO.getId());
            return ok().body(payment);
        } catch (Exception exception) {
            return badRequest().body(exception.getMessage());
        }
    }
}
