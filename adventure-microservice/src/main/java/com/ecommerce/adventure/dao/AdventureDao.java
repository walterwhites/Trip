package com.ecommerce.adventure.dao;

import com.ecommerce.adventure.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdventureDao extends JpaRepository<Adventure, Integer> {
    public Adventure findById(int id);
}
