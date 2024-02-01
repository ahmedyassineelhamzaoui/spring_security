package com.example.security.services.impl;

import com.example.security.models.entity.AppPermission;
import com.example.security.models.entity.AppRole;
import com.example.security.repositories.PermissionRepository;
import com.example.security.repositories.RoleRepository;
import com.example.security.services.facades.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    @Override
    @Transactional
    public void AddPermissionToRole(String roleName, String permissionName) {
        AppRole role = roleRepository.findByAuthority(roleName).orElseThrow(() -> new NoSuchElementException("No role found"));
        AppPermission permission = permissionRepository.findByName(permissionName).orElseThrow(() -> new NoSuchElementException("permission not found"));
        if(role.getPermissions() !=null){
            role.getPermissions().add(permission);
            permission.getRoles().add(role);
        }
    }
}
