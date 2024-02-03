package com.example.security.services.facades.email;

public interface VerificationCodeService {
    boolean verifyExistCode(String code);
}
