package com.example.security.services.impl.email;

import com.example.security.repositories.UserRepository;
import com.example.security.services.facades.email.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final UserRepository userRepository;
    @Override
    public boolean verifyExistCode(String code) {
        return userRepository.findByVerificationCode(code).isEmpty();
    }
}
