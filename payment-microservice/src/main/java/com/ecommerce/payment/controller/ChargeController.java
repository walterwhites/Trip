package com.ecommerce.payment.controller;

import com.ecommerce.payment.requestDTO.ChargeRequestDTO;
import com.ecommerce.payment.responseDTO.ClientResponseDTO;
import com.ecommerce.payment.service.ClientService;
import com.ecommerce.payment.service.PaymentService;
import com.ecommerce.payment.service.impl.ClientServiceImpl;
import com.ecommerce.payment.utils.CookiesUtils;
import com.stripe.exception.StripeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping()
@Api(value = "This is charge controller")
public class ChargeController {

    private final PaymentService paymentservice;
    private final ClientServiceImpl clientService;

    @Autowired
    public ChargeController(PaymentService paymentservice, ClientServiceImpl clientService) {
        this.paymentservice = paymentservice;
        this.clientService = clientService;
    }

    @InitBinder
    public void allowEmptyDateBinding(WebDataBinder binder)
    {
        // set empty values as null instead of empty string.
        binder.registerCustomEditor( String.class, new StringTrimmerEditor( true ));
    }

    @ModelAttribute("logged")
    public Boolean bindCookies(HttpServletRequest request) {
        String jwt_token = CookiesUtils.getCookie(request, "jwt_token");
        return (jwt_token != null && !jwt_token.isEmpty());
    }

    @PostMapping(value="charge")
    @ApiOperation(value = "Make a payment")
    @ResponseBody
    public ResponseEntity<?> charge(@RequestBody ChargeRequestDTO chargeRequestDTO) {
        try {
            Optional<ClientResponseDTO> clientResponseDTO = clientService.getUserInformations();
            return ok().body(paymentservice.charge(chargeRequestDTO, clientResponseDTO));
        } catch (StripeException stripeException) {
            return badRequest().body(stripeException.getMessage());
        }
    }
}