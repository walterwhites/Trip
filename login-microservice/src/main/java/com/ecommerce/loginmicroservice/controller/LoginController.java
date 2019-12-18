package com.ecommerce.loginmicroservice.controller;

import com.ecommerce.loginmicroservice.constants.WebResourceKeyConstants;
import com.ecommerce.loginmicroservice.exceptionHandler.DataDuplicationException;
import com.ecommerce.loginmicroservice.exceptionHandler.UnauthorisedException;
import com.ecommerce.loginmicroservice.requestDTO.LoginRequestDTO;
import com.ecommerce.loginmicroservice.requestDTO.RegisterRequestDTO;
import com.ecommerce.loginmicroservice.service.LoginService;
import com.ecommerce.loginmicroservice.service.RegisterService;
import com.ecommerce.loginmicroservice.service.impl.LoginServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = WebResourceKeyConstants.BASE_API)
@Api(value = "This is login controller")
public class LoginController {

    private final LoginService loginService;
    private final RegisterService registerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    public LoginController(LoginService loginService, RegisterService registerService) {
        this.loginService = loginService;
        this.registerService = registerService;
    }

    @PostMapping(value = WebResourceKeyConstants.LOGIN)
    @ApiOperation(value = "This is login api", notes = "Request contains username and password")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) {
        try {
            String token = loginService.login(requestDTO, request);
            return ok().body(token);
        } catch (UnauthorisedException unauthorisedException) {
            LOGGER.info("dev_message: " + unauthorisedException.getErrorResponse().getDeveloperMsg());
            return ResponseEntity.status(unauthorisedException.getErrorResponse().getStatus()).body(unauthorisedException.getErrorResponse().getErrorMsg());
        }
    }

    @PostMapping(value = WebResourceKeyConstants.REGISTER)
    @ApiOperation(value = "This is register api", notes = "Request contains client informations")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) {
        registerService.register(requestDTO, request);
        return ok().body("");
    }
}