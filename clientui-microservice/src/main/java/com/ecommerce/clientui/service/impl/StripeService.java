package com.ecommerce.clientui.service.impl;

import com.ecommerce.clientui.beans.ChargeRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Charge charge(ChargeRequest chargeRequest, String customerId, Card card) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", card.getId());
        chargeParams.put("customer", customerId);
        return Charge.create(chargeParams);
    }

    public Customer createCustomer(String email) {
        Customer customer = null;
        try {
            Stripe.apiKey = secretKey;
            Map<String, Object> customerParams = new HashMap<>();
            // add customer unique id here to track them in your web application
            customerParams.put("description", "Customer for " + email);
            customerParams.put("email", email);

            //create a new customer
            customer = Customer.create(customerParams);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customer;
    }

    public Card createAcard(Customer customer, String token) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("source", token);
        Card card = (Card) customer.getSources().create(params);
        return card;
    }
}