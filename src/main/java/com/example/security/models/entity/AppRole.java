package com.example.security.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppRole {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private Set<AppPermission> permissions = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppUser> users = new HashSet<>();

}
