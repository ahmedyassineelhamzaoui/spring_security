package com.example.security.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message="email is required")
    private String email;

    @NotBlank(message = "password is required")
    private String password;
}
