package com.ecommerce.payment.repositories.impl;

import com.ecommerce.payment.repositories.PaymentRepositoryCustom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class PaymentRepositoryCustomImpl implements PaymentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
}
