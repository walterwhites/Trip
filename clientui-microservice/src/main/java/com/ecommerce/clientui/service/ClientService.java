package com.ecommerce.clientui.service;

import com.ecommerce.clientui.responseDTO.ClientResponseDTO;
import java.util.Optional;

public interface ClientService {
    public Optional<ClientResponseDTO> getUserInformations();
}
