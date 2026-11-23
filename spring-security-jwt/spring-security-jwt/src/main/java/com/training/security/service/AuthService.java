package com.training.security.service;

import com.training.security.dto.JwtResponse;
import com.training.security.dto.LoginRequest;
import com.training.security.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    JwtResponse login(LoginRequest request);
}
