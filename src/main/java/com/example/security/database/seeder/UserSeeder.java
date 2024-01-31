package com.example.security.database.seeder;

import com.example.security.models.entity.AppUser;
import com.example.security.repositories.RoleRepository;
import com.example.security.repositories.UserRepository;
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
    private final RoleRepository roleRepository;

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
            assignRolesToUsers();
        }
    }
    private void assignRolesToUsers(){
        AppUser user1 = userRepository.findByUsername("john123").orElseThrow(() -> new UsernameNotFoundException("username not found"));
        user1.setAuthorities(roleRepository.findAll());
        userRepository.save(user1);

        AppUser user2 = userRepository.findByUsername("jane456").orElseThrow(() -> new UsernameNotFoundException("username not found"));
        user1.setAuthorities(List.of(
                roleRepository.findByName("ADMIN").orElseThrow(() -> new NoSuchElementException("role not found")),
                roleRepository.findByName("USER").orElseThrow(() -> new NoSuchElementException("role not found"))
        ));
        userRepository.save(user2);

        AppUser user3 = userRepository.findByUsername("mike789").orElseThrow(() -> new UsernameNotFoundException("username not found"));
        user3.setAuthorities(List.of(
                roleRepository.findByName("ADMIN").orElseThrow(() -> new NoSuchElementException("role not found")),
                roleRepository.findByName("USER").orElseThrow(() -> new NoSuchElementException("role not found"))
        ));
        userRepository.save(user3);

        AppUser user4 = userRepository.findByUsername("sarah1011").orElseThrow(() -> new UsernameNotFoundException("username not found"));
        user4.setAuthorities(List.of(
                roleRepository.findByName("ADMIN").orElseThrow(() -> new NoSuchElementException("role not found"))
        ));
        userRepository.save(user4);

        AppUser user5 = userRepository.findByUsername("rob1213").orElseThrow(() -> new UsernameNotFoundException("username not found"));
        user5.setAuthorities(List.of(
                roleRepository.findByName("USER").orElseThrow(() -> new NoSuchElementException("role not found"))
        ));
        userRepository.save(user5);
    }
}
