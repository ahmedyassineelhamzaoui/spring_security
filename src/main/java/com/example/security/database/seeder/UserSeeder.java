package com.example.security.database.seeder;

import com.example.security.models.entity.AppUser;
import com.example.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSeeder {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void seed(){
        if(userRepository.count() == 0){
            List<AppUser> users = List.of(
                    AppUser.builder()
                            .firstName("John")
                            .lastName("Doe")
                            .username("john123")
                            .password(passwordEncoder.encode("1234Password@!"))
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build(),
            AppUser.builder()
                    .firstName("Jane")
                    .lastName("Doe")
                    .username("jane456")
                    .password(passwordEncoder.encode("1234Password@!"))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build(),
            AppUser.builder()
                    .firstName("Mike")
                    .lastName("Smith")
                    .username("mike789")
                    .password(passwordEncoder.encode("1234Password@!"))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build(),
            AppUser.builder()
                    .firstName("Sarah")
                    .lastName("Williams")
                    .username("sarah1011")
                    .password(passwordEncoder.encode("1234Password@!"))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build(),
            AppUser.builder()
                    .firstName("Robert")
                    .lastName("Johnson")
                    .username("rob1213")
                    .password(passwordEncoder.encode("1234Password@!"))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build()
            );
            userRepository.saveAll(users);
        }
    }
}
