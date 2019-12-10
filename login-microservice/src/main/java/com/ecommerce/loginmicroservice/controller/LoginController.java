package com.ecommerce.loginmicroservice.controller;

import com.ecommerce.loginmicroservice.constants.WebResourceKeyConstants;
import com.ecommerce.loginmicroservice.exceptionHandler.UnauthorisedException;
import com.ecommerce.loginmicroservice.requestDTO.LoginRequestDTO;
import com.ecommerce.loginmicroservice.service.LoginService;
import com.ecommerce.loginmicroservice.service.impl.LoginServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = WebResourceKeyConstants.BASE_API)
@Api(value = "This is login controller")
public class LoginController {

    private final LoginService loginService;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = WebResourceKeyConstants.LOGIN)
    @ApiOperation(value = "This is login api", notes = "Request contains username and password")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO requestDTO, HttpServletRequest request) {
        try {
            String token = loginService.login(requestDTO, request);
        } catch (UnauthorisedException unauthorisedException) {
            LOGGER.info("dev_message: " + unauthorisedException.getErrorResponse().getDeveloperMsg());
            return ResponseEntity.status(unauthorisedException.getErrorResponse().getStatus()).body(unauthorisedException.getErrorResponse().getErrorMsg());
        }
        return ok().body(loginService.login(requestDTO, request));
    }

    @GetMapping("/test")
    public String test() {
        return "test done";
    }
}