package com.example.security.database.seeder;

import com.example.security.models.entity.AppPermission;
import com.example.security.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PermissionSeeder {

    private final PermissionRepository permissionRepository;

    public void seed(){
        if(permissionRepository.count() == 0){
            List<AppPermission> permissions = List.of(
                    AppPermission.builder().name("CAN_EDIT").build(),
                    AppPermission.builder().name("CAN_ADD").build(),
                    AppPermission.builder().name("CAN_DELETE").build(),
                    AppPermission.builder().name("CAN_ADD_POST").build()
            );
            permissionRepository.saveAll(permissions);
        }
    }
}
