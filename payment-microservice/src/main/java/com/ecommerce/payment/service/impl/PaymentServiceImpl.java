package com.ecommerce.payment.service.impl;

import com.ecommerce.payment.beans.ChargeRequest;
import com.ecommerce.payment.model.Payment;
import com.ecommerce.payment.repositories.PaymentRepository;
import com.ecommerce.payment.requestDTO.ChargeRequestDTO;
import com.ecommerce.payment.responseDTO.ChargeResponseDTO;
import com.ecommerce.payment.responseDTO.ClientResponseDTO;
import com.ecommerce.payment.responseDTO.PaymentResponseDTO;
import com.ecommerce.payment.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


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
    public ChargeResponseDTO charge(ChargeRequestDTO chargeRequestDTO, Optional<ClientResponseDTO> clientResponseDTO) throws StripeException {

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


        int amount = chargeRequestDTO.getChargeRequest().getAmount() / 100; // (in cents)
        Payment payment = new Payment();
        payment.setClientId(clientResponseDTO.get().getId());
        payment.setAdventure(chargeRequestDTO.getAdventure());
        payment.setAmount(amount);
        payment.setState("paid");
        payment.setCommandId(chargeRequestDTO.getChargeRequest().getStripeToken());
        payment.setPaymentDate(LocalDateTime.now());

        paymentRepository.save(payment);
        return chargeResponseDTO;
    }

    @Override
    public List<PaymentResponseDTO> getPayments(Long clientId) {
        List<Payment> payments = paymentRepository.fetchAllClientPayments(clientId);
        ModelMapper modelMapper = new ModelMapper();
        List<PaymentResponseDTO> listPaymentResponseDTO = Arrays.asList(modelMapper.map(payments, PaymentResponseDTO[].class));

        return listPaymentResponseDTO;
    }

    @Override
    public PaymentResponseDTO getPayment(int id) {
        Optional<Payment> payment = paymentRepository.getPaymentById(id);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(payment.get(), PaymentResponseDTO.class);
    }
}
