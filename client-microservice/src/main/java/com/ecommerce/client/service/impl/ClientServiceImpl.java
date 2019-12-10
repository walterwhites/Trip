package com.ecommerce.client.service.impl;

import com.ecommerce.client.exceptions.NoContentFoundException;
import com.ecommerce.client.model.Client;
import com.ecommerce.client.repositories.ClientRepository;
import com.ecommerce.client.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@Transactional
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public void saveClient(Client client) {

        log.info(":::: SAVE CLIENT PROCESS STARTED::::");
        validateClientRequestDTO.accept(client);

        System.out.println("VALIDATION DONE");
    }

    public Consumer<Client> validateClientRequestDTO = (client) -> {
//        adminRepository.fetchAdminByUsername(requestDTO.getUsername()).ifPresent(admin -> {
//            throw new DataDuplicationException(DUPLICATE_USERNAME_MESSAGE, DUPLICATE_USERNAME_DEVELOPER_MESSAGE);
//        });
//
//        adminRepository.fetchAdminByEmailAddress(requestDTO.getEmailAddress()).ifPresent(admin -> {
//            throw new DataDuplicationException(DUPLICATE_EMAILADDRESS_MESSAGE, DUPLICATE_EMAILADDRESS_DEVELOPER_MESSAGE);
//        });
    };

    @Override
    public Optional<Client> searchClient(Client client) throws NoContentFoundException {
        return clientRepository.fetchClientByUsername(client.getUsername());
    }

    @Override
    public Client updateClient(Client client) {
        return null;
    }

    @Override
    public Optional<Client> fetchClientByUsername(String username) {
        return clientRepository.fetchClientByUsername(username);
    }

    @Override
    public List<Client> clientsToSendEmails() {
        List<Client> clientBeanList = clientRepository.fetchAllClientsToSendEmails();
        return clientBeanList;
    }

    @Override
    public List<Client> fetchAllClient() {
        return clientRepository.findAll();
    }
}
