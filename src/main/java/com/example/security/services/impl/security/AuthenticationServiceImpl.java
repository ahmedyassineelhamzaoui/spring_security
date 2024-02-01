package com.example.security.services.impl.security;

import com.example.security.common.exceptions.custom.EmailVerificationException;
import com.example.security.common.exceptions.custom.UserAllReadyExistException;
import com.example.security.models.dto.request.LoginRequest;
import com.example.security.models.dto.request.SignupRequest;
import com.example.security.models.dto.response.LoginResponse;
import com.example.security.models.dto.response.SignupResponse;
import com.example.security.models.entity.AppUser;
import com.example.security.repositories.UserRepository;
import com.example.security.services.facades.security.AuthenticationService;
import com.example.security.services.facades.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
                     AppUser user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("email not exist "));
                     if(!user.isEnabled()){
                            throw new EmailVerificationException("please verify your email to enable your account");
                     }else{
                            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
                            var jwtAccessToken = jwtService.generateAccessToken(user);
                            var jwtRefreshToken = jwtService.generateRefreshToken(user);
                            return LoginResponse.builder().firstName(user.getFirstName()).lastName(user.getLastName()).accessToken(jwtAccessToken).refreshToken(jwtRefreshToken).build();
                     }
              }catch(AuthenticationException e){
                     throw new IllegalArgumentException("invalid email or password");
              }


       }

       @Override
       public SignupResponse signup(SignupRequest request) {
              userRepository.findByEmail(request.getEmail())
                                                                  .ifPresent(
                                                                          (AppUser existingUser)->  {throw  new UserAllReadyExistException("User with this email already exist");}
                                                                  );
              var user = AppUser.builder().firstName(request.getFirstName()).lastName(request.getLastName()).email(request.getEmail()).enabled(false).credentialsNonExpired(true).accountNonLocked(true).accountNonExpired(true).authorities(new ArrayList<>()).password(passwordEncoder.encode(request.getPassword())).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();
              userRepository.save(user);
              return SignupResponse.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getUsername()).accountNonExpired(user.isAccountNonExpired()).accountNonLocked(user.isAccountNonLocked()).credentialsNonExpired(user.isCredentialsNonExpired()).enabled(user.isEnabled()).createdAt(user.getCreatedAt()).updatedAt(user.getUpdatedAt()).build();
       }

}
