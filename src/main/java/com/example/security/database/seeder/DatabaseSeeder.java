package com.example.security.database.seeder;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final RoleSeeder roleSeeder;
    private final PermissionSeeder permissionSeeder;
    private final UserSeeder userSeeder;

    @PostConstruct
    public void init(){
        roleSeeder.seed();
        permissionSeeder.seed();
        userSeeder.seed();
    }
}
