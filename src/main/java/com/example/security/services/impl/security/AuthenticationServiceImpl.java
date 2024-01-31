package com.example.security.services.impl.security;

import com.example.security.repositories.UserRepository;
import com.example.security.services.facades.security.AuthenticationService;
import com.example.security.services.facades.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl  implements AuthenticationService {
       private final UserRepository userRepository;
       private final PasswordEncoder passwordEncoder;
       private final JwtService jwtService;
       private final AuthenticationManager authenticationManager;

}
