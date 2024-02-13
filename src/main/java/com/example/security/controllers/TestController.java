package com.example.security.controllers;

import com.example.security.common.responses.ResponseWithoutDetails;
import com.example.security.services.facades.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {

    private final ResponseWithoutDetails responseWithoutDetails;
    private final PermissionService permissionService;
    @GetMapping("/superAdmin")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<ResponseWithoutDetails> superAdminAccess() {
            responseWithoutDetails.setTimesTamps(LocalDateTime.now());
            responseWithoutDetails.setStatus("200");
            responseWithoutDetails.setMessage("Super Admin Access");
            return ResponseEntity.ok(responseWithoutDetails);
    }

    @GetMapping("/admin")
    public ResponseEntity<ResponseWithoutDetails> adminAccess() {
        responseWithoutDetails.setTimesTamps(LocalDateTime.now());
        responseWithoutDetails.setStatus("200");
        responseWithoutDetails.setMessage("Admin Access");
        return ResponseEntity.ok(responseWithoutDetails);
    }

    @GetMapping("/add")
    public ResponseEntity<ResponseWithoutDetails> addAccess() {
        responseWithoutDetails.setTimesTamps(LocalDateTime.now());
        responseWithoutDetails.setStatus("200");
        responseWithoutDetails.setMessage("Add Access");
        return ResponseEntity.ok(responseWithoutDetails);
    }

    @GetMapping("/write")
    public ResponseEntity<ResponseWithoutDetails> writeAccess() {
        if(permissionService.checkPermission("CAN_ADD_POST")){
            responseWithoutDetails.setTimesTamps(LocalDateTime.now());
            responseWithoutDetails.setStatus("200");
            responseWithoutDetails.setMessage("Write Access");
            return ResponseEntity.ok(responseWithoutDetails);
        }
        return null;
    }

}
