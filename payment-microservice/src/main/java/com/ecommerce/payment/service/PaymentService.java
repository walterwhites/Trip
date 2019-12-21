package com.ecommerce.payment.service;

import com.ecommerce.payment.requestDTO.ChargeRequestDTO;
import com.ecommerce.payment.responseDTO.ChargeResponseDTO;
import com.ecommerce.payment.responseDTO.ClientResponseDTO;
import com.ecommerce.payment.responseDTO.PaymentResponseDTO;
import com.stripe.exception.StripeException;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    ChargeResponseDTO charge(ChargeRequestDTO chargeRequestDTO, Optional<ClientResponseDTO> clientResponseDTO) throws StripeException;
    List<PaymentResponseDTO> getPayments(Long client);
    public PaymentResponseDTO getPayment(int id);
}
