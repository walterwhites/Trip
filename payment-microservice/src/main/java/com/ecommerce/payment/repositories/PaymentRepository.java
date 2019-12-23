package com.ecommerce.payment.repositories;

import com.ecommerce.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>, PaymentRepositoryCustom {

    @Query(value = "SELECT p FROM Payment p WHERE p.id = :id")
    Optional<Payment> getPaymentById(@Param("id") int id);

    @Query(value = "SELECT p FROM Payment p WHERE p.chargeId = :chargeId")
    Optional<Payment> getPaymentByChargeId(@Param("chargeId") String chargeId);

    @Query(value = "SELECT * FROM payment", nativeQuery = true)
    List<Payment> fetchAllPayments();

    @Query(value = "SELECT p FROM Payment p WHERE p.clientId = :client")
    List<Payment> fetchAllClientPayments(@Param("client") Long client);

}