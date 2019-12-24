package com.ecommerce.adventure.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Qualifier("AdventureRepositoryCustom")
@Transactional
public interface AdventureRepositoryCustom {
}
