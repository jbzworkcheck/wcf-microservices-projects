package com.wipro.productservice.service;

import com.wipro.productservice.dto.security.JwtResponse;
import com.wipro.productservice.dto.security.LoginRequest;
import com.wipro.productservice.dto.security.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    JwtResponse login(LoginRequest request);
}
