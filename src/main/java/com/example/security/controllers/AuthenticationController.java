package com.example.security.controllers;

import com.example.security.services.facades.UserService;
import com.example.security.services.facades.security.AuthenticationService;
import com.example.security.services.facades.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtService jwtService;

}
