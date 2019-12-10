package com.ecommerce.client.service;

import com.ecommerce.client.model.Client;
import java.util.List;
import java.util.Optional;

public interface ClientService {

    void saveClient(Client client);
    Optional<Client> searchClient(Client client);
    Client updateClient(Client client);
    Optional<Client> fetchClientByUsername(String username);
    List<Client> clientsToSendEmails();
    List<Client> fetchAllClient();
}
