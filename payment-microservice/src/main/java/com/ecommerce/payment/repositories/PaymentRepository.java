package com.ecommerce.payment.repositories;

import com.ecommerce.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>, PaymentRepositoryCustom {

    @Query(value = "SELECT * FROM payment WHERE id = :id", nativeQuery = true)
    Optional<Payment> getPaymentById(@Param("id") Long id);

    @Query(value = "SELECT * FROM payment", nativeQuery = true)
    List<Payment> fetchAllPayments();

}