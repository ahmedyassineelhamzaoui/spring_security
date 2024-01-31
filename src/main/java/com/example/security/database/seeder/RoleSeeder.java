package com.example.security.database.seeder;

import com.example.security.models.entity.AppRole;
import com.example.security.repositories.PermissionRepository;
import com.example.security.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class RoleSeeder {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    public void seed(){
        if(roleRepository.count() == 0){
            List<AppRole> roles = List.of(
                    AppRole.builder().name("SUPER_ADMIN").build(),
                    AppRole.builder().name("ADMIN").build(),
                    AppRole.builder().name("USER").build()
            );
            roleRepository.saveAll(roles);
            assignPermissionsToRoles();
        }
    }
    private void assignPermissionsToRoles() {
        AppRole superAdmin = roleRepository.findByName("SUPER_ADMIN").orElseThrow(() -> new NoSuchElementException("role not found"));
        superAdmin.setPermissions(permissionRepository.findAll());
        roleRepository.save(superAdmin);

        AppRole admin = roleRepository.findByName("ADMIN").orElseThrow(() -> new NoSuchElementException("role not found"));
        admin.setPermissions(List.of(
                permissionRepository.findByName("CAN_ADD")
                        .orElseThrow(() -> new NoSuchElementException("permission not found")),
                permissionRepository.findByName("CAN_EDIT")
                        .orElseThrow(() -> new NoSuchElementException("permission not found"))
        ));
        roleRepository.save(admin);

        AppRole user = roleRepository.findByName("USER").orElseThrow(() -> new NoSuchElementException("role not found"));
        user.setPermissions(List.of(
                        permissionRepository.findByName("CAN_ADD_POST")
                                .orElseThrow(() -> new NoSuchElementException("permission not found"))
                )
        );
        roleRepository.save(user);
    }
}
