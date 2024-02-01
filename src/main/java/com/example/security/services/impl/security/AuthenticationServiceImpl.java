package com.example.security.services.impl.security;

import com.example.security.common.exceptions.custom.UserAllReadyExistException;
import com.example.security.models.dto.request.LoginRequest;
import com.example.security.models.dto.request.SignupRequest;
import com.example.security.models.dto.response.LoginResponse;
import com.example.security.models.dto.response.SignupResponse;
import com.example.security.models.entity.AppUser;
import com.example.security.repositories.UserRepository;
import com.example.security.services.facades.security.AuthenticationService;
import com.example.security.services.facades.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl  implements AuthenticationService {
       private final UserRepository userRepository;
       private final PasswordEncoder passwordEncoder;
       private final JwtService jwtService;
       private final AuthenticationManager authenticationManager;


       @Override
       public LoginResponse login( LoginRequest request) {
              try{
                     authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
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
              userRepository.findByUsername(request.getUsername())
                                                                  .ifPresent(
                                                                          () ->  {throw  new UserAllReadyExistException("User with this username already exist")}
                                                                  );
              var user = AppUser.builder().firstName(request.getFirstName()).lastName(request.getLastName()).username(request.getUsername()).authorities(new ArrayList<>()).password(passwordEncoder.encode(request.getPassword())).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();
              userRepository.save(user);
              return SignupResponse.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName()).username(user.getUsername()).accountNonExpired(user.isAccountNonExpired()).accountNonLocked(user.isAccountNonLocked()).credentialsNonExpired(user.isCredentialsNonExpired()).enabled(user.isEnabled()).createdAt(user.getCreatedAt()).updatedAt(user.getUpdatedAt()).build();
       }
}
