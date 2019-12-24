package com.ecommerce.payment.service;

import com.ecommerce.payment.responseDTO.ClientResponseDTO;

import java.util.Optional;

public interface ClientService {
    public Optional<ClientResponseDTO> getUserInformations();
}
