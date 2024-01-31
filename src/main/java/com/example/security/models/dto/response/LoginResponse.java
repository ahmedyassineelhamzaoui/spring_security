package com.example.security.models.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {

    private String firstName;
    private String lastName;
    private String accessToken;
    private String refreshToken;
}
