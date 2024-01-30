package com.example.security.repositories;

import com.example.security.models.entity.AppPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<AppPermission, UUID> {
}
