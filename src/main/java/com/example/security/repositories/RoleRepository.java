package com.example.security.repositories;

import com.example.security.models.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<AppRole, UUID> {
    Optional<AppRole> findByName(String name);
}
