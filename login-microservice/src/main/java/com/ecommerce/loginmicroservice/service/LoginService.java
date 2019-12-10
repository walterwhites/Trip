package com.ecommerce.loginmicroservice.service;

import com.ecommerce.loginmicroservice.model.Client;
import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    String login(Client client, HttpServletRequest request) throws RuntimeException;
}