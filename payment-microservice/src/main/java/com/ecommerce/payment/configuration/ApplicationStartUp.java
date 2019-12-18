package com.ecommerce.payment.configuration;

import com.ecommerce.payment.model.Payment;
import com.ecommerce.payment.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import java.util.Date;
import java.util.List;

@Component
@EnableConfigurationProperties(StartupProperties.class)
public class ApplicationStartUp {

    @Autowired
    private StartupProperties startupProperties;

    @Bean
    public CommandLineRunner loadData(PaymentRepository paymentRepository) {
        return (args) -> {
            List<Payment> payments = paymentRepository.findAll();

            if (ObjectUtils.isEmpty(payments)) {
                paymentRepository.save(savePayment());
            }
        };
    }

    public Payment savePayment() {
        Payment payment = new Payment();
        payment.setClientId(startupProperties.getClientId());
        payment.setAdventureId(startupProperties.getAdventureId());
        payment.setAmount(startupProperties.getAmount());
        payment.setState(startupProperties.getState());
        payment.setCommandId(startupProperties.getCommandId());
        payment.setPaymentDate(new Date());

        return payment;
    }
}
