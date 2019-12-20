package com.ecommerce.payment.service.impl;

import com.ecommerce.payment.beans.ChargeRequest;
import com.ecommerce.payment.repositories.PaymentRepository;
import com.ecommerce.payment.requestDTO.ChargeRequestDTO;
import com.ecommerce.payment.responseDTO.ChargeResponseDTO;
import com.ecommerce.payment.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StripeService stripeService;

    @Override
    public ChargeResponseDTO charge(ChargeRequestDTO chargeRequestDTO) throws StripeException {

        chargeRequestDTO.getChargeRequest().setDescription("Book adventure: " + chargeRequestDTO.getAdventure());
        chargeRequestDTO.getChargeRequest().setCurrency(ChargeRequest.Currency.EUR);
        Customer customer = stripeService.createCustomer(chargeRequestDTO.getEmail(), chargeRequestDTO.getName());
        Card card = stripeService.createAcard(customer, chargeRequestDTO.getChargeRequest().getStripeToken());
        Charge charge = stripeService.charge(chargeRequestDTO.getChargeRequest(), customer.getId(), card);

        ChargeResponseDTO chargeResponseDTO = new ChargeResponseDTO();
        chargeResponseDTO.setId(charge.getId());
        chargeResponseDTO.setStatus(charge.getStatus());
        chargeResponseDTO.setChargeId(charge.getId());
        chargeResponseDTO.setBalance_transaction(charge.getBalanceTransaction());
        return chargeResponseDTO;
    }
}
