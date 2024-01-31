package com.example.security.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

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
    private List<AppPermission> permissions = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppUser> users = new ArrayList<>();

}
