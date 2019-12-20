package com.ecommerce.payment.service;

import com.ecommerce.payment.requestDTO.ChargeRequestDTO;
import com.ecommerce.payment.responseDTO.ChargeResponseDTO;
import com.stripe.exception.StripeException;

public interface PaymentService {

    ChargeResponseDTO charge(ChargeRequestDTO chargeRequestDTO) throws StripeException;
}
