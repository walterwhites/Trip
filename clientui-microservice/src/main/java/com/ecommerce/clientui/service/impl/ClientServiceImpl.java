package com.ecommerce.clientui.service.impl;

import com.ecommerce.clientui.constants.ErrorMessage;
import com.ecommerce.clientui.exception.UnauthorisedException;
import com.ecommerce.clientui.proxies.MicroserviceClientProxy;
import com.ecommerce.clientui.responseDTO.ClientResponseDTO;
import com.ecommerce.clientui.security.jwt.JwtTokenProvider;
import com.ecommerce.clientui.service.ClientService;
import com.ecommerce.clientui.utils.CookiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import static com.ecommerce.clientui.constants.SecurityConstants.*;

@Service
@Transactional("transactionManager")
public class ClientServiceImpl implements ClientService {

    private HttpServletRequest request;
    private MicroserviceClientProxy microserviceClientProxy;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public ClientServiceImpl(HttpServletRequest request, MicroserviceClientProxy microserviceClientProxy, JwtTokenProvider jwtTokenProvider) {
        this.request = request;
        this.microserviceClientProxy = microserviceClientProxy;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Optional<ClientResponseDTO> getUserInformations() throws UnauthorisedException {
        try {
            String token = CookiesUtils.getCookie(request, JWT_COOKIE);
            jwtTokenProvider.validateToken(token);
            String username = jwtTokenProvider.getUsername(token);
            Optional<ClientResponseDTO> clientResponseDTO = microserviceClientProxy.fetchClientByUsername(username, request.getHeader(REFERER_HEADER), request.getHeader(AUTHORIZATION_HEADER));
            clientResponseDTO.get().setUsername(username);
            return clientResponseDTO;
        } catch (UnauthorisedException unauthorisedException) {
            throw new UnauthorisedException(ErrorMessage.TokenInvalid.MESSAGE, ErrorMessage.TokenInvalid.DEVELOPER_MESSAGE);
        }
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