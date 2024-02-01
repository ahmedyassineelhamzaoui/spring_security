package com.example.security.controllers;

import com.example.security.common.responses.ResponseWithoutDetails;
import com.example.security.models.dto.request.LoginRequest;
import com.example.security.models.dto.request.SignupRequest;
import com.example.security.models.dto.response.LoginResponse;
import com.example.security.models.dto.response.SignupResponse;
import com.example.security.services.facades.UserService;
import com.example.security.services.facades.security.AuthenticationService;
import com.example.security.services.facades.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody @Valid SignupRequest request){
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }


}
