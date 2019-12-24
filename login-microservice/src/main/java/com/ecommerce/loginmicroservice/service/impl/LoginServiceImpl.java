package com.ecommerce.loginmicroservice.service.impl;

import com.ecommerce.loginmicroservice.constants.PatternConstants;
import com.ecommerce.loginmicroservice.exceptionHandler.MissingFieldException;
import com.ecommerce.loginmicroservice.exceptionHandler.UnauthorisedException;
import com.ecommerce.loginmicroservice.feignInterface.ClientInterface;
import com.ecommerce.loginmicroservice.jwt.JwtTokenProvider;
import com.ecommerce.loginmicroservice.requestDTO.ClientRequestDTO;
import com.ecommerce.loginmicroservice.requestDTO.LoginRequestDTO;
import com.ecommerce.loginmicroservice.responseDTO.ClientResponseDTO;
import com.ecommerce.loginmicroservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.ecommerce.loginmicroservice.constants.ErrorMessageConstants.*;

import static com.ecommerce.loginmicroservice.constants.ErrorMessageConstants.MissingField.*;
import static com.ecommerce.loginmicroservice.constants.SecurityConstants.REFERER_HEADER;

@Service
@Transactional("transactionManager")
public class LoginServiceImpl implements LoginService {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ClientInterface clientInterface;

    private HttpServletRequest request;

    @Override
    public String login(LoginRequestDTO requestDTO, HttpServletRequest request) throws RuntimeException {
        this.request = request;
        validateFields.accept(requestDTO);
        ClientResponseDTO client = fetchClientDetails.apply(requestDTO);
        validateClientStatus.accept(client);
        validatePassword.accept(requestDTO, client);
        return jwtTokenProvider.createToken(requestDTO.getUsername(), request);
    }

    private Function<LoginRequestDTO, ClientResponseDTO> fetchClientDetails = (loginRequestDTO) -> {
        Pattern pattern = Pattern.compile(PatternConstants.EmailConstants.EMAIL_PATTERN);
        Matcher m = pattern.matcher(loginRequestDTO.getUsername());
        Boolean find = m.find();

        ClientResponseDTO clientResponseDTO =  find ? clientInterface.searchClient(ClientRequestDTO.builder().username(null).emailAddress(loginRequestDTO.getUsername()).build(), request.getHeader(REFERER_HEADER)) :
                clientInterface.searchClient(ClientRequestDTO.builder().username(loginRequestDTO.getUsername()).emailAddress(null).build(), request.getHeader(REFERER_HEADER));
        if (clientResponseDTO.getEmailAddress() == null) {
            throw new UnauthorisedException(InvalidCredentials.MESSAGE, InvalidClientUsername.DEVELOPER_MESSAGE);
        }
        return clientResponseDTO;
    };

    private Consumer<ClientResponseDTO> validateClientStatus = (client) -> {
        switch (client.getStatus()) {
            case 'B':
                throw new UnauthorisedException(InvalidClientStatus.MESSAGE_FOR_BLOCKED,
                        InvalidClientStatus.DEVELOPER_MESSAGE_FOR_BLOCKED);

            case 'N':
                throw new UnauthorisedException(InvalidClientStatus.MESSAGE_FOR_INACTIVE,
                        InvalidClientStatus.DEVELOPER_MESSAGE_FOR_INACTIVE);
        }
    };

    private BiConsumer<LoginRequestDTO, ClientResponseDTO> validatePassword = (requestDTO, client) -> {
        if (BCrypt.checkpw(requestDTO.getPassword(), client.getPassword())) {
            client.setLoginAttempt(0);
            clientInterface.updateClient(client);
        } else {
            throw new UnauthorisedException(InvalidCredentials.MESSAGE, ForgetPassword.DEVELOPER_MESSAGE);
        }
    };

    private Consumer<LoginRequestDTO> validateFields = (requestDTO) -> {
        if (requestDTO.getUsername() == null) {
            throw new MissingFieldException(MISSING_USERNAME_MESSAGE, MISSING_USERNAME_MESSAGE);
        } else if (requestDTO.getPassword() == null) {
            throw new MissingFieldException(MISSING_PASSWORD_MESSAGE, MISSING_PASSWORD_MESSAGE);
        }
    };

}