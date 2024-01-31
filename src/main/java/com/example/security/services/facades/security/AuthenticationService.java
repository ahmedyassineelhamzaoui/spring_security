package com.example.security.services.facades.security;

import com.example.security.models.dto.request.LoginRequest;
import com.example.security.models.dto.request.SignupRequest;
import com.example.security.models.dto.response.LoginResponse;
import com.example.security.models.dto.response.SignupResponse;

public interface AuthenticationService {
     LoginResponse login(LoginRequest request);
     SignupResponse signup(SignupRequest request);
}
