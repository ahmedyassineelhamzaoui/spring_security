package com.example.security.services.impl;

import com.example.security.models.entity.AppPermission;
import com.example.security.models.entity.AppRole;
import com.example.security.repositories.RoleRepository;
import com.example.security.services.facades.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private  final RoleRepository roleRepository;
    @Override
    public boolean checkPermission(String permission) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for(GrantedAuthority grantedAuthority : auth.getAuthorities()){
            AppRole role = roleRepository.findByAuthority(grantedAuthority.getAuthority()).orElseThrow(() -> new NoSuchElementException("role nor found"));
            for(AppPermission permissionToFind : role.getPermissions()){
                if(permissionToFind.getName().equals(permission)){
                    return true;
                }
            }
        }
        return false;
    }
}
