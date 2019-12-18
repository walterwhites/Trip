package com.ecommerce.client.service;

import com.ecommerce.client.exceptions.DataDuplicationException;
import com.ecommerce.client.exceptions.MissingFieldException;
import com.ecommerce.client.exceptions.NoContentFoundException;
import com.ecommerce.client.model.Client;
import com.ecommerce.client.requestDTO.ClientRequestDTO;
import com.ecommerce.client.requestDTO.RegisterRequestDTO;
import com.ecommerce.client.responseDTO.ClientResponseDTO;
import com.ecommerce.client.responseDTO.ResponseDTO;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ClientService {

    void saveClient(RegisterRequestDTO requestDTO) throws DataDuplicationException, MissingFieldException;
    ClientResponseDTO searchClient(ClientRequestDTO requestDTO) throws NoContentFoundException;
    Client updateClient(ClientRequestDTO requestDTO);
    ClientResponseDTO fetchClientByUsername(String username);
    ResponseDTO clientsToSendEmails();
    List<Client> fetchAllClient();
}
