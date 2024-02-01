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
                    AppRole.builder().authority("SUPER_ADMIN").build(),
                    AppRole.builder().authority("ADMIN").build(),
                    AppRole.builder().authority("USER").build()
            );
            roleRepository.saveAll(roles);
            assignPermissionsToRoles();
        }
    }
    private void assignPermissionsToRoles() {
        AppRole superAdmin = roleRepository.findByAuthority("SUPER_ADMIN").orElseThrow(() -> new NoSuchElementException("role not found"));
        superAdmin.setPermissions(permissionRepository.findAll());
        roleRepository.save(superAdmin);

        AppRole admin = roleRepository.findByAuthority("ADMIN").orElseThrow(() -> new NoSuchElementException("role not found"));
        admin.setPermissions(List.of(
                permissionRepository.findByName("CAN_ADD")
                        .orElseThrow(() -> new NoSuchElementException("permission not found")),
                permissionRepository.findByName("CAN_EDIT")
                        .orElseThrow(() -> new NoSuchElementException("permission not found"))
        ));
        roleRepository.save(admin);

        AppRole user = roleRepository.findByAuthority("USER").orElseThrow(() -> new NoSuchElementException("role not found"));
        user.setPermissions(List.of(
                        permissionRepository.findByName("CAN_ADD_POST")
                                .orElseThrow(() -> new NoSuchElementException("permission not found"))
                )
        );
        roleRepository.save(user);
    }
}
