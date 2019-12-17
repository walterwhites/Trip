package com.ecommerce.loginmicroservice.service.impl;

import com.ecommerce.loginmicroservice.exceptionHandler.DataDuplicationException;
import com.ecommerce.loginmicroservice.exceptionHandler.MissingFieldException;
import com.ecommerce.loginmicroservice.feignInterface.ClientInterface;
import com.ecommerce.loginmicroservice.jwt.JwtTokenProvider;
import com.ecommerce.loginmicroservice.requestDTO.RegisterRequestDTO;
import com.ecommerce.loginmicroservice.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedList;
import java.util.function.Function;
import static com.ecommerce.loginmicroservice.constants.SecurityConstants.REFERER_HEADER;

@Service
@Transactional("transactionManager")
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ClientInterface clientInterface;

    private HttpServletRequest request;

    @Override
    public String register(RegisterRequestDTO requestDTO, HttpServletRequest request) throws DataDuplicationException, MissingFieldException {
        try {
            this.request = request;
            RegisterRequestDTO client = registerClient.apply(requestDTO);
            clientInterface.saveClient(client, request.getHeader(REFERER_HEADER));
            return "";
        } catch (DataDuplicationException dataDuplicationException) {
            throw new DataDuplicationException(dataDuplicationException.getMessage(), dataDuplicationException.getMessage());
        } catch (MissingFieldException missingFieldException) {
            throw new MissingFieldException(missingFieldException.getMessage(), missingFieldException.getMessage());
        }
    }

    private Function<RegisterRequestDTO, RegisterRequestDTO> registerClient = (registerRequestDTO) -> {
        if (registerRequestDTO.getPassword() != null) {
            registerRequestDTO.setPassword(BCrypt.hashpw(registerRequestDTO.getPassword(), BCrypt.gensalt()));
        }
        registerRequestDTO.setStatus('Y');
        registerRequestDTO.setCreatedDate(new Date());
        registerRequestDTO.setLastModifiedDate(new Date());
        LinkedList<String> roles = new LinkedList<>();
        roles.add("CLIENT");
        registerRequestDTO.setRoles(roles);
        return registerRequestDTO;
    };
}