package com.ecommerce.adventure.dao;

import com.ecommerce.adventure.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdventureDao extends JpaRepository<Adventure, Integer>, AdventureRepositoryCustom {

    Adventure findById(int id);

    Adventure findByName(String name);

    @Modifying
    @Query(value = "UPDATE adventure SET max_entrant = :max_entrant WHERE name = :adv_name", nativeQuery = true)
    void updateMaxEntrant(@Param("max_entrant") int max_entrant, @Param("adv_name") String adv_name);
}