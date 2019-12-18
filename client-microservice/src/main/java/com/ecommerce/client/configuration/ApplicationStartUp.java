package com.ecommerce.client.configuration;

import com.ecommerce.client.model.Client;
import com.ecommerce.client.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

@Component
@EnableConfigurationProperties(StartupProperties.class)
public class ApplicationStartUp {

    @Autowired
    private StartupProperties startupProperties;

    @Bean
    public CommandLineRunner loadData(ClientRepository clientRepository) {
        return (args) -> {
            List<Client> clients = clientRepository.findAll();

            if (ObjectUtils.isEmpty(clients)) {
                clientRepository.save(saveClient());
            }
        };
    }

    public Client saveClient() {
        Client client = new Client();
        client.setUsername(startupProperties.getUsername());
        client.setFirstname(startupProperties.getFirstname());
        client.setLastname(startupProperties.getLastname());
        client.setFullName(startupProperties.getFullName());
        client.setPassword(BCrypt.hashpw(startupProperties.getPassword(), BCrypt.gensalt()));
        client.setEmailAddress(startupProperties.getEmailAddress());
        client.setStatus(startupProperties.getStatus());
        client.setRoles(Arrays.asList("CLIENT"));

        return client;
    }
}
