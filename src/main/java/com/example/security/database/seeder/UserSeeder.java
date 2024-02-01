package com.example.security.database.seeder;

import com.example.security.models.entity.AppUser;
import com.example.security.repositories.RoleRepository;
import com.example.security.repositories.UserRepository;
import com.example.security.services.facades.UserService;
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
    private final UserService userService;

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

        userService.AddRoleToUser("john123@gmail.com", "SUPER_ADMIN");
        userService.AddRoleToUser("john123@gmail.com", "ADMIN");
        userService.AddRoleToUser("john123@gmail.com", "USER");

        userService.AddRoleToUser("jane456@gmail.com", "SUPER_ADMIN");
        userService.AddRoleToUser("jane456@gmail.com", "ADMIN");

        userService.AddRoleToUser("mike789@gmail.com", "SUPER_ADMIN");
        userService.AddRoleToUser("mike789@gmail.com", "ADMIN");

        userService.AddRoleToUser("sarah1011@gmail.com", "ADMIN");

        userService.AddRoleToUser("rob1213@gmail.com", "USER");

    }
}
