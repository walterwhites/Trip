package com.ecommerce.payment.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("PaymentRepositoryCustom")
public interface PaymentRepositoryCustom {
}
