package com.ecommerce.client.service;

import com.ecommerce.client.model.Client;
import com.ecommerce.client.requestDTO.ClientRequestDTO;
import com.ecommerce.client.responseDTO.ClientResponseDTO;
import com.ecommerce.client.responseDTO.ResponseDTO;

import java.util.List;

public interface ClientService {

    void saveClient(ClientRequestDTO requestDTO);
    ClientResponseDTO searchClient(ClientRequestDTO requestDTO);
    Client updateClient(ClientRequestDTO requestDTO);
    ClientResponseDTO fetchClientByUsername(String username);
    ResponseDTO clientsToSendEmails();
    List<Client> fetchAllClient();
}
