package com.example.security.controllers;

import com.example.security.common.responses.ResponseWithDetails;
import com.example.security.common.responses.ResponseWithoutDetails;
import com.example.security.repositories.UserRepository;
import com.example.security.services.facades.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class UserController {

    private final UserService userService;
    private final ResponseWithDetails responseWithDetails;
    private final ResponseWithoutDetails responseWithoutDetails;

    @GetMapping("users")
    public ResponseEntity<ResponseWithDetails> getAllUsers(){

        Map<String,Object > users = new HashMap<>();
        responseWithDetails.setTimesTamps(LocalDateTime.now());
        responseWithDetails.setStatus("200");
        responseWithDetails.setMessage("users  successfully");
        users.put("users",userService.getAllUsers());
        responseWithDetails.setDetails(users);
        return ResponseEntity.status(HttpStatus.FOUND).body(responseWithDetails);
    }
}
