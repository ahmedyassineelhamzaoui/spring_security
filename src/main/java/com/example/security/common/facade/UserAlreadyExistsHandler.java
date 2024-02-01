package com.example.security.common.facade;

@FunctionalInterface
public interface UserAlreadyExistsHandler {
    void handle();
}
