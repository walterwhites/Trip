package com.ecommerce.payment.service;

import com.ecommerce.payment.model.Payment;
import com.ecommerce.payment.requestDTO.ChargeRequestDTO;
import com.ecommerce.payment.responseDTO.ChargeResponseDTO;
import com.ecommerce.payment.responseDTO.ClientResponseDTO;
import com.ecommerce.payment.responseDTO.PaymentResponseDTO;
import com.stripe.exception.StripeException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface PaymentService {

    ChargeResponseDTO charge(ChargeRequestDTO chargeRequestDTO, Optional<ClientResponseDTO> clientResponseDTO) throws StripeException;
    List<PaymentResponseDTO> getPayments(Long client);
    PaymentResponseDTO getPayment(int id);
    void refundCard(String chargeId) throws StripeException;
    void reduceMaxEntrant(String chargeId, String adventure, HttpServletRequest request);
    void upMaxEntrant(String chargeId, String adventure, HttpServletRequest request);
}
