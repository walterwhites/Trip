package com.ecommerce.payment.service.impl;

import com.ecommerce.payment.beans.AdventureBean;
import com.ecommerce.payment.beans.ChargeRequest;
import com.ecommerce.payment.model.Payment;
import com.ecommerce.payment.proxies.MicroserviceAdventureProxy;
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
import com.stripe.model.Refund;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

import static com.ecommerce.payment.constants.SecurityConstants.AUTHORIZATION_HEADER;
import static com.ecommerce.payment.constants.SecurityConstants.REFERER_HEADER;


@Service
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private MicroserviceAdventureProxy microserviceAdventureProxy;

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
        payment.setChargeId(charge.getId());

        paymentRepository.save(payment);
        return chargeResponseDTO;
    }

    @Override
    public void reduceMaxEntrant(String chargeId, String adventure, HttpServletRequest request) {
        microserviceAdventureProxy.reduceEntrantsAdventure(chargeId, adventure, request.getHeader(REFERER_HEADER), request.getHeader(AUTHORIZATION_HEADER));
    }

    @Override
    public void upMaxEntrant(String chargeId, String adventure, HttpServletRequest request) {
        microserviceAdventureProxy.reduceEntrantsAdventure(chargeId, adventure, request.getHeader(REFERER_HEADER), request.getHeader(AUTHORIZATION_HEADER));
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

    @Override
    public void refundCard(String chargeId) throws StripeException {
        Payment payment = paymentRepository.getPaymentByChargeId(chargeId).get();
        Map<String, Object> params = new HashMap<>();
        params.put("charge", chargeId);
        Refund.create(params);
        payment.setState("canceled");
        paymentRepository.save(payment);
    }
}
