package com.ecommerce.clientui.service.impl;

import com.ecommerce.clientui.beans.ChargeRequest;
import com.ecommerce.clientui.proxies.MicroservicePaymentProxy;
import com.ecommerce.clientui.requestDTO.ChargeRequestDTO;
import com.ecommerce.clientui.responseDTO.ChargeResponseDTO;
import com.ecommerce.clientui.service.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import static org.springframework.http.HttpHeaders.*;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private MicroservicePaymentProxy microservicePaymentProxy;

    @Override
    public Optional<ChargeResponseDTO> charge(ChargeRequest chargeRequest, ChargeRequestDTO chargeRequestDTO, @RequestParam String email, @RequestParam String name, @RequestParam String adventure, HttpServletRequest request) throws StripeException {

        chargeRequestDTO.setChargeRequest(chargeRequest);
        chargeRequestDTO.setEmail(email);
        chargeRequestDTO.setName(name);
        chargeRequestDTO.setAdventure(adventure);

        return microservicePaymentProxy.charge(chargeRequestDTO, request.getHeader(REFERER), request.getHeader(AUTHORIZATION));
    }
}
