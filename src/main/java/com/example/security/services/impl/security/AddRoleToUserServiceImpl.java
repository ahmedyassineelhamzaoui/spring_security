package com.example.security.services.impl.security;

import com.example.security.models.entity.AppRole;
import com.example.security.models.entity.AppUser;
import com.example.security.repositories.RoleRepository;
import com.example.security.repositories.UserRepository;
import com.example.security.services.facades.security.AddRoleToUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AddRoleToUserServiceImpl implements AddRoleToUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

        @Override
        @Transactional
        public void addRoleToUser(String email, String roleName) {
            AppUser user= userRepository.findByEmail(email).orElseThrow(()-> new NoSuchElementException("User not found"));
            AppRole role =  roleRepository.findByAuthority(roleName).orElseThrow(() -> new NoSuchElementException("Role not found"));

            if (user.getAuthorities() != null ) {
                user.getAuthorities().add(role);
                role.getUsers().add(user);
            }

        }

}
