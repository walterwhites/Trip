package com.ecommerce.adventure.dao;

import com.ecommerce.adventure.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdventureDao extends JpaRepository<Adventure, Integer> {

    Adventure findById(int id);

    Adventure findByName(String name);

    @Modifying
    @Query(value = "UPDATE adventure SET max_entrant = :max_entrant", nativeQuery = true)
    void reduceMaxEntrant(@Param("max_entrant") int max_entrant);
}