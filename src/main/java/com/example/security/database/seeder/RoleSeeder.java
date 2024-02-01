package com.example.security.database.seeder;

import com.example.security.models.entity.AppRole;
import com.example.security.repositories.PermissionRepository;
import com.example.security.repositories.RoleRepository;
import com.example.security.services.facades.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class RoleSeeder {
    private final RoleRepository roleRepository;
    private final RoleService roleService;
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

        roleService.AddPermissionToRole("SUPER_ADMIN","CAN_ADD");
        roleService.AddPermissionToRole("SUPER_ADMIN","CAN_DELETE");
        roleService.AddPermissionToRole("SUPER_ADMIN","CAN_ADD_POST");
        roleService.AddPermissionToRole("SUPER_ADMIN","CAN_EDIT");

        roleService.AddPermissionToRole("ADMIN","CAN_ADD");
        roleService.AddPermissionToRole("ADMIN","CAN_EDIT");
        roleService.AddPermissionToRole("ADMIN","CAN_ADD_POST");

        roleService.AddPermissionToRole("USER","CAN_ADD_POST");

    }
}
