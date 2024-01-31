package com.example.security.database.seeder;

import com.example.security.models.entity.AppRole;
import com.example.security.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleSeeder {
    private final RoleRepository roleRepository;

    public void seed(){
        if(roleRepository.count() == 0){
            List<AppRole> roles = List.of(
                    AppRole.builder().name("SUPER_ADMIN").build(),
                    AppRole.builder().name("ADMIN").build(),
                    AppRole.builder().name("USER").build()
            );
            roleRepository.saveAll(roles);
        }
    }
}
