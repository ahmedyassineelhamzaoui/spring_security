package com.example.security.services.impl.security;

import com.example.security.services.facades.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    @Override
    public String extractUserName(String token) {
        return null;
    }

    @Override
    public String generateAccessToken(UserDetails userDetails) {
        return null;
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        return null;
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return false;
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResolvers){
        final Claims claims =extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }
}
