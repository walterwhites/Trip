package com.ecommerce.loginmicroservice.service;

import com.ecommerce.loginmicroservice.requestDTO.RegisterRequestDTO;
import javax.servlet.http.HttpServletRequest;

public interface RegisterService {
    String register(RegisterRequestDTO requestDTO, HttpServletRequest request) throws RuntimeException;
}