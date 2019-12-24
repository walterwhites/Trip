package com.ecommerce.clientui.service;

import com.ecommerce.clientui.beans.ChargeRequest;
import com.ecommerce.clientui.requestDTO.ChargeRequestDTO;
import com.ecommerce.clientui.responseDTO.ChargeResponseDTO;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface PaymentService {

    Optional<ChargeResponseDTO> charge(ChargeRequest chargeRequest, ChargeRequestDTO chargeRequestDTO, @RequestParam String email, @RequestParam String name, @RequestParam String adventure, HttpServletRequest request) throws StripeException;
}
