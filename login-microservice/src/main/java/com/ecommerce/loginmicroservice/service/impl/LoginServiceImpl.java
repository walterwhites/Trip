package com.ecommerce.loginmicroservice.service.impl;

import com.ecommerce.loginmicroservice.constants.PatternConstants;
import com.ecommerce.loginmicroservice.exceptionHandler.UnauthorisedException;
import com.ecommerce.loginmicroservice.feignInterface.ClientInterface;
import com.ecommerce.loginmicroservice.jwt.JwtTokenProvider;
import com.ecommerce.loginmicroservice.model.Client;
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
    public String login(Client client, HttpServletRequest request) throws RuntimeException {
        this.request = request;
        Client client1 = fetchClientDetails.apply(client);
        validateClientStatus.accept(client1);
        validatePassword.accept(client, client1);
        return jwtTokenProvider.createToken(client1.getUsername(), request);
    }

    private Function<Client, Client> fetchClientDetails = (client) -> {
        Pattern pattern = Pattern.compile(PatternConstants.EmailConstants.EMAIL_PATTERN);
        Matcher m = pattern.matcher(client.getUsername());
        Boolean find = m.find();
        Client client1 = find ? clientInterface.searchClient(client, request.getHeader(REFERER_HEADER)) :
                clientInterface.searchClient(client, request.getHeader(REFERER_HEADER));
        if (client1 == null) {
            throw new UnauthorisedException(InvalidCredentials.MESSAGE, InvalidClientUsername.DEVELOPER_MESSAGE);
        }
        return client1;
    };

    private Consumer<Client> validateClientStatus = (client) -> {
        switch (client.getStatus()) {
            case 'B':
                throw new UnauthorisedException(InvalidClientStatus.MESSAGE_FOR_BLOCKED,
                        InvalidClientStatus.DEVELOPER_MESSAGE_FOR_BLOCKED);

            case 'N':
                throw new UnauthorisedException(InvalidClientStatus.MESSAGE_FOR_INACTIVE,
                        InvalidClientStatus.DEVELOPER_MESSAGE_FOR_INACTIVE);
        }
    };

    private BiConsumer<Client, Client> validatePassword = (request, client) -> {
        if (BCrypt.checkpw(request.getPassword(), client.getPassword())) {
            client.setLoginAttempt(0);
            clientInterface.updateClient(client);
        } else {
            throw new UnauthorisedException(InvalidCredentials.MESSAGE, ForgetPassword.DEVELOPER_MESSAGE);
        }
    };

}