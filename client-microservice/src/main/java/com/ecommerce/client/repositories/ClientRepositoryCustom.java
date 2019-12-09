package com.ecommerce.client.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("ClientRepositoryCustom")
public interface ClientRepositoryCustom {
}
