package com.example.security.repositories;

import com.example.security.enums.Provider;
import com.example.security.models.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {
     Optional<AppUser> findByEmail(String username);
     Optional<AppUser> findByVerificationCode(String code);

}
