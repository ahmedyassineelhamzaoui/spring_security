package com.example.security.services.facades;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();
    void AddRoleToUser(String username,String roleName);

}
