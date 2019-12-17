package com.ecommerce.client.service.impl;

import com.ecommerce.client.constants.ErrorMessage;
import com.ecommerce.client.exceptions.DataDuplicationException;
import com.ecommerce.client.exceptions.MissingFieldException;
import com.ecommerce.client.exceptions.NoContentFoundException;
import com.ecommerce.client.model.Client;
import com.ecommerce.client.repositories.ClientRepository;
import com.ecommerce.client.requestDTO.ClientRequestDTO;
import com.ecommerce.client.requestDTO.RegisterRequestDTO;
import com.ecommerce.client.responseDTO.ClientResponseDTO;
import com.ecommerce.client.responseDTO.ResponseDTO;
import com.ecommerce.client.service.ClientService;
import com.ecommerce.client.utils.ClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.ecommerce.client.constants.ErrorMessage.DataDuplication.*;
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
    public void saveClient(RegisterRequestDTO requestDTO) throws DataDuplicationException, MissingFieldException {

        log.info(":::: SAVE CLIENT PROCESS STARTED::::");
        validateRequestDTOFields.accept(requestDTO);
        validateRegisterRequestDTO.accept(requestDTO);
        ModelMapper modelMapper = new ModelMapper();
        Client client = modelMapper.map(requestDTO, Client.class);
        clientRepository.save(client);
        System.out.println("VALIDATION DONE");
    }

    private Consumer<RegisterRequestDTO> validateRegisterRequestDTO = (requestDTO) -> {
        clientRepository.fetchClientByUsername(requestDTO.getUsername()).ifPresent(client -> {
            DataDuplicationException dataDuplicationException = new DataDuplicationException(DUPLICATE_USERNAME_MESSAGE, DUPLICATE_USERNAME_DEVELOPER_MESSAGE);
            dataDuplicationException.getErrorResponse().setStatus(HttpStatus.CONFLICT);
            throw dataDuplicationException;
        });

        clientRepository.fetchClientByUsername(requestDTO.getEmailAddress()).ifPresent(client -> {
            DataDuplicationException dataDuplicationException = new DataDuplicationException(DUPLICATE_EMAILADDRESS_MESSAGE, DUPLICATE_EMAILADDRESS_DEVELOPER_MESSAGE);
            dataDuplicationException.getErrorResponse().setStatus(HttpStatus.CONFLICT);
            throw dataDuplicationException;
        });
    };

    private Consumer<RegisterRequestDTO> validateRequestDTOFields = (requestDTO) -> {
        if (requestDTO.getEmailAddress() == null) {
            throw new MissingFieldException(ErrorMessage.MissingField.MISSING_EMAIL_MESSAGE, ErrorMessage.MissingField.MISSING_EMAIL_MESSAGE);
        } else if (requestDTO.getUsername() == null) {
            throw new MissingFieldException(ErrorMessage.MissingField.MISSING_USERNAME_MESSAGE, ErrorMessage.MissingField.MISSING_USERNAME_MESSAGE);
        } else if (requestDTO.getFirstname() == null) {
            throw new MissingFieldException(ErrorMessage.MissingField.MISSING_FIRSTNAME_MESSAGE, ErrorMessage.MissingField.MISSING_FIRSTNAME_MESSAGE);
        } else if (requestDTO.getLastname() == null) {
            throw new MissingFieldException(ErrorMessage.MissingField.MISSING_LASTNAME_MESSAGE, ErrorMessage.MissingField.MISSING_LASTNAME_MESSAGE);
        } else if (requestDTO.getPassword() == null) {
            throw new MissingFieldException(ErrorMessage.MissingField.MISSING_PASSWORD_MESSAGE, ErrorMessage.MissingField.MISSING_PASSWORD_MESSAGE);
        }
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
