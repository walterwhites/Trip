package com.ecommerce.clientui.service.impl;


import com.ecommerce.clientui.proxies.MicroserviceClientProxy;
import com.ecommerce.clientui.responseDTO.ClientResponseDTO;
import com.ecommerce.clientui.security.jwt.JwtProperties;
import com.ecommerce.clientui.service.ClientService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Optional;

import static com.ecommerce.clientui.constants.SecurityConstants.AUTHORIZATION_HEADER;
import static com.ecommerce.clientui.constants.SecurityConstants.REFERER_HEADER;

@Service
@Transactional("transactionManager")
public class ClientServiceImpl implements ClientService {

    private HttpServletRequest request;

    @Autowired
    MicroserviceClientProxy microserviceClientProxy;

    private String secretKey;

    public ClientServiceImpl(HttpServletRequest request, JwtProperties jwtProperties) {
        this.request = request;
        this.secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
    }


    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Optional<ClientResponseDTO> getUserInformations(String token) {
        String username = getUsername(token);
        return microserviceClientProxy.fetchClientByUsername(username, request.getHeader(REFERER_HEADER), request.getHeader(AUTHORIZATION_HEADER));
    }

/*
    @Override
    public String getClientInformations(HttpServletRequest request) throws RuntimeException {
        this.request = request;
        ClientResponseDTO client = fetchClientDetails.apply(request.getHeader("Authorization"));
        validateClientStatus.accept(client);
        validatePassword.accept(requestDTO, client);
        return jwtTokenProvider.createToken(requestDTO.getUsername(), request);
    }

    private Function<String, ClientResponseDTO> fetchClientDetails = (loginRequestDTO) -> {

        ClientResponseDTO clientResponseDTO =  find ? microserviceClientProxy.searchClient(ClientRequestDTO.builder().username(null).emailAddress(loginRequestDTO.getUsername()).build(), request.getHeader(REFERER_HEADER)) :
                clientInterface.searchClient(ClientRequestDTO.builder().username(loginRequestDTO.getUsername()).emailAddress(null).build(), request.getHeader(REFERER_HEADER));
        if (clientResponseDTO.getEmailAddress() == null) {
            throw new UnauthorisedException(InvalidCredentials.MESSAGE, InvalidClientUsername.DEVELOPER_MESSAGE);
        }
        return clientResponseDTO;
    }; */
}