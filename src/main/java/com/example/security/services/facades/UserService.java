package com.example.security.services.facades;

import com.example.security.models.dto.response.AppUserDTO;
import com.example.security.models.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService();

    List<AppUserDTO> getAllUsers();

}
