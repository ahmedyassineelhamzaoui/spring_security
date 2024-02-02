package com.example.security.services.impl.security;

import com.example.security.common.exceptions.custom.EmailVerificationException;
import com.example.security.common.exceptions.custom.UserAllReadyExistException;
import com.example.security.models.dto.request.LoginRequest;
import com.example.security.models.dto.request.SignupRequest;
import com.example.security.models.dto.response.AppUserDTO;
import com.example.security.models.dto.response.LoginResponse;
import com.example.security.models.entity.AppUser;
import com.example.security.repositories.UserRepository;
import com.example.security.services.facades.UserService;
import com.example.security.services.facades.email.EmailServiceSender;
import com.example.security.services.facades.security.AuthenticationService;
import com.example.security.services.facades.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl  implements AuthenticationService {

       private final UserRepository userRepository;
       private final PasswordEncoder passwordEncoder;
       private final JwtService jwtService;
       private final AuthenticationManager authenticationManager;
       private final UserService userService;
       private final EmailServiceSender emailServiceSender;

       @Override
       public LoginResponse login( LoginRequest request) {
              try{
                     AppUser user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("email not exist "));
                     if(!user.isEnabled()){
                            throw new EmailVerificationException("please verify your email to enable your account");
                     }else{
                            if(!user.isCredentialsNonExpired()){
                                   user.setCredentialsNonExpired(true);
                                   userRepository.save(user);
                            }
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
       public AppUserDTO signup(SignupRequest request) {
              userRepository.findByEmail(request.getEmail())
                                      .ifPresent(
                                              (AppUser existingUser)->  {throw  new UserAllReadyExistException("User with this email already exist");}
                                      );
              String verificationCode = generateVerificationCode();
              var user = AppUser.builder().firstName(request.getFirstName()).lastName(request.getLastName()).email(request.getEmail()).verificationCode(verificationCode).enabled(false).credentialsNonExpired(true).accountNonLocked(true).accountNonExpired(true).authorities(new ArrayList<>()).password(passwordEncoder.encode(request.getPassword())).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();

              userRepository.save(user);
              userService.AddRoleToUser(user.getEmail(),"USER");
              String body = "Dear "+user.getFirstName()+ ",\n" +
                      "\n" +
                      "Thank you for registering with our service. To verify your email address, please use the following verification code:\n" +
                      "\n" +
                      "Verification Code: "+verificationCode+"\n" +
                      "\n" +
                      "Please enter this code on the verification page to complete the registration process.\n" +
                      "\n" +
                      "If you did not request this verification, please ignore this email.\n" +
                      "\n" +
                      "Best regards,";
              emailServiceSender.sendEmail(user.getEmail(),"Email Verification Code",body);
              return AppUserDTO.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getUsername()).accountNonExpired(user.isAccountNonExpired()).accountNonLocked(user.isAccountNonLocked()).credentialsNonExpired(user.isCredentialsNonExpired()).enabled(user.isEnabled()).createdAt(user.getCreatedAt()).updatedAt(user.getUpdatedAt()).build();
       }

       @Override
       public LoginResponse verifyEmail(String email,String code) {
              var user = userRepository.findByEmail(email).orElseThrow(()-> new NoSuchElementException("User  not found with this email"));
              if(!user.getVerificationCode().equals(code)){
                     throw new EmailVerificationException("verification code wrong");
              }
              var jwtAccessToken = jwtService.generateAccessToken(user);
              var jwtRefreshToken = jwtService.generateRefreshToken(user);
              return LoginResponse.builder().firstName(user.getFirstName()).lastName(user.getLastName()).accessToken(jwtAccessToken).refreshToken(jwtRefreshToken).build();
       }

       private  String generateVerificationCode() {
              int codeLength = 6;
              StringBuilder verificationCode = new StringBuilder();
              for (int i = 0; i < codeLength; i++) {
                     verificationCode.append(new Random().nextInt(10));
              }

              return verificationCode.toString();
       }

}
