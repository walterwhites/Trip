package com.ecommerce.clientui.repositories;

import com.ecommerce.clientui.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, ClientRepositoryCustom {

    @Query(value = "SELECT * FROM client WHERE username = :username AND status = 'Y'", nativeQuery = true)
    Optional<Client> fetchClientByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM client WHERE email_address = :emailAddress AND status = 'Y'", nativeQuery = true)
    Optional<Client> fetchClientByEmailAddress(@Param("emailAddress") String emailAddress);

    @Query(value = "SELECT * FROM client WHERE id = :id", nativeQuery = true)
    Optional<Client> getClientById(@Param("id") Long id);

    @Query(value = "SELECT * FROM client", nativeQuery = true)
    List<Client> fetchAllClients();


}