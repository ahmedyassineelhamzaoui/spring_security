package com.example.security.models.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SignupResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private String createdAt;
    private String updatedAt;
}
