package com.ecommerce.comment.service;

import com.ecommerce.comment.responseDTO.ClientResponseDTO;

import java.util.Optional;

public interface ClientService {
    public Optional<ClientResponseDTO> getUserInformations();
}
