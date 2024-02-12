package com.example.security.database.seeder;

import com.example.security.enums.Provider;
import com.example.security.models.entity.AppUser;
import com.example.security.repositories.RoleRepository;
import com.example.security.repositories.UserRepository;
import com.example.security.services.facades.UserService;
import com.example.security.services.facades.security.AddRoleToUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class UserSeeder {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddRoleToUserService addRoleToUserService;

    public void seed(){
        if(userRepository.count() == 0){
            List<AppUser> users = List.of(
            AppUser.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .email("john123@gmail.com")
                    .accountNonLocked(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .provider(Provider.local)
                    .password(passwordEncoder.encode("1234Password@!"))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build(),
            AppUser.builder()
                    .firstName("Jane")
                    .lastName("Doe")
                    .email("jane456@gmail.com")
                    .accountNonLocked(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .provider(Provider.local)
                    .password(passwordEncoder.encode("1234Password@!"))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build(),
            AppUser.builder()
                    .firstName("Mike")
                    .lastName("Smith")
                    .email("mike789@gmail.com")
                    .accountNonLocked(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .provider(Provider.local)
                    .password(passwordEncoder.encode("1234Password@!"))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build(),
            AppUser.builder()
                    .firstName("Sarah")
                    .lastName("Williams")
                    .email("sarah1011@gmail.com")
                    .accountNonLocked(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .provider(Provider.local)
                    .password(passwordEncoder.encode("1234Password@!"))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build(),
            AppUser.builder()
                    .firstName("Robert")
                    .lastName("Johnson")
                    .email("rob1213@gmail.com")
                    .accountNonLocked(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .provider(Provider.local)
                    .password(passwordEncoder.encode("1234Password@!"))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build()
            );
            userRepository.saveAll(users);
            assignRolesToUsers();
        }
    }
    private void assignRolesToUsers(){

        addRoleToUserService.addRoleToUser("john123@gmail.com", "SUPER_ADMIN");
        addRoleToUserService.addRoleToUser("john123@gmail.com", "ADMIN");
        addRoleToUserService.addRoleToUser("john123@gmail.com", "USER");

        addRoleToUserService.addRoleToUser("jane456@gmail.com", "SUPER_ADMIN");
        addRoleToUserService.addRoleToUser("jane456@gmail.com", "ADMIN");

        addRoleToUserService.addRoleToUser("mike789@gmail.com", "SUPER_ADMIN");
        addRoleToUserService.addRoleToUser("mike789@gmail.com", "ADMIN");

        addRoleToUserService.addRoleToUser("sarah1011@gmail.com", "ADMIN");

        addRoleToUserService.addRoleToUser("rob1213@gmail.com", "USER");

    }
}
