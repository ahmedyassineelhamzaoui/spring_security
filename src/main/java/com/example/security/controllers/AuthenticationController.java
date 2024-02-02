package com.example.security.controllers;

import com.example.security.common.responses.ResponseWithDetails;
import com.example.security.common.responses.ResponseWithoutDetails;
import com.example.security.models.dto.request.LoginRequest;
import com.example.security.models.dto.request.SignupRequest;
import com.example.security.models.dto.request.VerificationEmailRequest;
import com.example.security.models.dto.response.LoginResponse;
import com.example.security.services.facades.UserService;
import com.example.security.services.facades.security.AuthenticationService;
import com.example.security.services.facades.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ResponseWithoutDetails responseWithoutDetails;
    private final ResponseWithDetails responseWithDetails;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseWithDetails> signup(@RequestBody @Valid SignupRequest request){
        Map<String,Object> user = new HashMap<>();
        user.put("user",authenticationService.signup(request));
        responseWithDetails.setTimesTamps(LocalDateTime.now());
        responseWithDetails.setStatus("200");
        responseWithDetails.setMessage("we send a code to your email");
        responseWithDetails.setDetails(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseWithDetails);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/verify-email")
    public ResponseEntity<LoginResponse> verifyEmail(@RequestBody @Valid VerificationEmailRequest request){
        return ResponseEntity.ok(authenticationService.verifyEmail(request.getCode(),request.getEmail()));
    }


}
