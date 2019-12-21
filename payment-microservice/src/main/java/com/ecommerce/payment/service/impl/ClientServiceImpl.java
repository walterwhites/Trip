package com.ecommerce.payment.service.impl;

import com.ecommerce.payment.constants.ErrorMessage;
import com.ecommerce.payment.exceptions.UnauthorisedException;
import com.ecommerce.payment.jwt.JwtTokenProvider;
import com.ecommerce.payment.proxies.MicroserviceClientProxy;
import com.ecommerce.payment.responseDTO.ClientResponseDTO;
import com.ecommerce.payment.service.ClientService;
import com.ecommerce.payment.utils.CookiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import static com.ecommerce.payment.constants.SecurityConstants.*;

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
            String token = request.getHeader(AUTHORIZATION_HEADER);
            token = substractBearerFromToken(token);
            jwtTokenProvider.validateToken(token);
            String username = jwtTokenProvider.getUsername(token);
            Optional<ClientResponseDTO> clientResponseDTO = microserviceClientProxy.fetchClientByUsername(username, request.getHeader(REFERER_HEADER), request.getHeader(AUTHORIZATION_HEADER));
            clientResponseDTO.get().setUsername(username);
            return clientResponseDTO;
        } catch (UnauthorisedException unauthorisedException) {
            throw new UnauthorisedException(ErrorMessage.TokenInvalid.MESSAGE, ErrorMessage.TokenInvalid.DEVELOPER_MESSAGE);
        }
    }

    private String substractBearerFromToken(String token) {
        return token.substring(7);
    }
}