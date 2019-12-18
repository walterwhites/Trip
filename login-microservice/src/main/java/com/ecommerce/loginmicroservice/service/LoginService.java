package com.ecommerce.loginmicroservice.service;

import com.ecommerce.loginmicroservice.requestDTO.LoginRequestDTO;
import com.ecommerce.loginmicroservice.requestDTO.RegisterRequestDTO;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    String login(LoginRequestDTO requestDTO, HttpServletRequest request) throws RuntimeException;
}