package com.example.security.models.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String firstName;
    private String lastName;
    private String accessToken;
    private String refreshToken;
}
