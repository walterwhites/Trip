package com.ecommerce.clientui.configuration;

import com.ecommerce.clientui.model.Client;
import com.ecommerce.clientui.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

@Component
public class ApplicationStartUp {

    private final StartupProperties startupProperties;

    @Autowired
    public ApplicationStartUp(StartupProperties startupProperties) {
        this.startupProperties = startupProperties;
    }

    @Bean
    public CommandLineRunner loadData(ClientRepository clientRepository) {
        return (args) -> {
            List<Client> clients = clientRepository.findAll();

            if (ObjectUtils.isEmpty(clients))
                clientRepository.save(saveClient());
        };
    }

    public Client saveClient() {
        Client client = new Client();
        client.setUsername(startupProperties.getUsername());
        client.setFullName(startupProperties.getFullName());
        client.setPassword(BCrypt.hashpw(startupProperties.getPassword(), BCrypt.gensalt()));
        client.setEmailAddress(startupProperties.getEmailAddress());
        client.setStatus(startupProperties.getStatus());
        client.setRoles(Arrays.asList("CLIENT"));

        return client;
    }
}
