package com.ecommerce.trip.dao;

import com.ecommerce.trip.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdventureDao extends JpaRepository<Adventure, Integer> {
    public Adventure findById(int id);
}
