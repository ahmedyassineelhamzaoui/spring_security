package com.example.security.models.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VerificationEmailRequest {

    private String code;
    private String email;
}
