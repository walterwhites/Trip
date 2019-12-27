package com.ecommerce.clientui.service;

import com.ecommerce.clientui.constants.ErrorMessage;
import com.ecommerce.clientui.exception.UnauthorisedException;
import com.ecommerce.clientui.proxies.MicroserviceClientProxy;
import com.ecommerce.clientui.responseDTO.ClientResponseDTO;
import com.ecommerce.clientui.security.jwt.JwtTokenProvider;
import com.ecommerce.clientui.service.impl.ClientServiceImpl;
import com.ecommerce.clientui.utils.CookiesUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.ecommerce.clientui.constants.SecurityConstants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ClientServiceImplTests {

}
