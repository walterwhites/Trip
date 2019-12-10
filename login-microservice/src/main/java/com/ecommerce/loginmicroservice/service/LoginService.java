package com.ecommerce.loginmicroservice.service;

import com.ecommerce.loginmicroservice.beans.ClientBean;
import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    String login(ClientBean clientBean, HttpServletRequest request) throws RuntimeException;
}