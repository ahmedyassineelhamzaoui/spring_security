package com.example.security.services.facades.security;

import com.example.security.models.dto.request.LoginRequest;
import com.example.security.models.dto.request.SignupRequest;
import com.example.security.models.dto.response.AppUserDTO;
import com.example.security.models.dto.response.LoginResponse;

public interface AuthenticationService  {
     LoginResponse login(LoginRequest request);
     AppUserDTO signup(SignupRequest request);

     LoginResponse verifyEmail(String code);
}
