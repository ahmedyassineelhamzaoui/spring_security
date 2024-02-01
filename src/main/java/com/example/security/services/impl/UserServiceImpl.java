package com.example.security.services.impl;

import com.example.security.models.entity.AppRole;
import com.example.security.models.entity.AppUser;
import com.example.security.repositories.RoleRepository;
import com.example.security.repositories.UserRepository;
import com.example.security.services.facades.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(" user not found"));
            }
        };
    }

    @Override
    @Transactional
    public void AddRoleToUser(String username, String roleName) {
        AppUser user= userRepository.findByUsername(username).orElseThrow(()-> new NoSuchElementException("User not found"));
        AppRole role =  roleRepository.findByAuthority(roleName).orElseThrow(() -> new NoSuchElementException("Role not found"));

        if (user.getAuthorities() != null ) {
            user.getAuthorities().add(role);
            role.getUsers().add(user);
        }

    }
}
