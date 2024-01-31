package com.example.security.services.impl.security;

import com.example.security.models.dto.request.LoginRequest;
import com.example.security.models.dto.request.SignupRequest;
import com.example.security.models.dto.response.LoginResponse;
import com.example.security.models.dto.response.SignupResponse;
import com.example.security.models.entity.AppUser;
import com.example.security.repositories.UserRepository;
import com.example.security.services.facades.security.AuthenticationService;
import com.example.security.services.facades.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl  implements AuthenticationService {
       private final UserRepository userRepository;
       private final PasswordEncoder passwordEncoder;
       private final JwtService jwtService;
       private final AuthenticationManager authenticationManager;


       @Override
       public LoginResponse login(LoginRequest request) {
              try{
                     authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()))
              }catch(AuthenticationException e){
                     throw new IllegalArgumentException("invalid username or password");
              }
              AppUser user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("username not exist "));
              var jwtAccessToken = jwtService.generateAccessToken(user);
              var jwtRefreshToken = jwtService.generateRefreshToken(user);
              return LoginResponse.builder().firstName(user.getFirstName()).lastName(user.getLastName()).accessToken(jwtAccessToken).refreshToken(jwtRefreshToken).build();
       }

       @Override
       public SignupResponse signup(SignupRequest request) {
              ;
       }
}
