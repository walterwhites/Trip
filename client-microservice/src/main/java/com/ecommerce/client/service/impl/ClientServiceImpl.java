package com.ecommerce.client.service.impl;

import com.ecommerce.client.exceptions.NoContentFoundException;
import com.ecommerce.client.model.Client;
import com.ecommerce.client.repositories.ClientRepository;
import com.ecommerce.client.requestDTO.ClientRequestDTO;
import com.ecommerce.client.responseDTO.ClientResponseDTO;
import com.ecommerce.client.responseDTO.ResponseDTO;
import com.ecommerce.client.service.ClientService;
import com.ecommerce.client.utils.ClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.ecommerce.client.queries.ClientQuery.*;
import static com.ecommerce.client.utils.ClientUtils.*;

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
    public void saveClient(ClientRequestDTO requestDTO) {

        log.info(":::: SAVE CLIENT PROCESS STARTED::::");
        validateClientRequestDTO.accept(requestDTO);

        System.out.println("VALIDATION DONE");
    }

    public Consumer<ClientRequestDTO> validateClientRequestDTO = (requestDTO) -> {
//        adminRepository.fetchAdminByUsername(requestDTO.getUsername()).ifPresent(admin -> {
//            throw new DataDuplicationException(DUPLICATE_USERNAME_MESSAGE, DUPLICATE_USERNAME_DEVELOPER_MESSAGE);
//        });
//
//        adminRepository.fetchAdminByEmailAddress(requestDTO.getEmailAddress()).ifPresent(admin -> {
//            throw new DataDuplicationException(DUPLICATE_EMAILADDRESS_MESSAGE, DUPLICATE_EMAILADDRESS_DEVELOPER_MESSAGE);
//        });
    };

    @Override
    public ClientResponseDTO searchClient(ClientRequestDTO requestDTO) throws NoContentFoundException {
        List<Object[]> results = entityManager.createNativeQuery(
                createQueryToFetchClientDetails.apply(requestDTO)).getResultList();
        return convertToClientResponse.apply(results);
    }

    @Override
    public Client updateClient(ClientRequestDTO requestDTO) {
        return null;
    }

    @Override
    public ClientResponseDTO fetchClientByUsername(String username) {
        List<Object[]> results = entityManager.createNativeQuery(
                createQueryToFetchClientByUsername.apply(username)).getResultList();
        return convertToClientResponse.apply(results);
    }

    @Override
    public ResponseDTO clientsToSendEmails() {

        List<Object[]> results = entityManager.createNativeQuery(
                createQueryToFetchClientToSendEmail.get()).getResultList();

        List<ClientResponseDTO> responseDTOS = results.stream().map(ClientUtils.convertToResponse)
                .collect(Collectors.toList());

        return ResponseDTO.builder().adminResponseDTOS(responseDTOS).build();
    }

    @Override
    public List<Client> fetchAllClient() {
        return null;
    }
}
