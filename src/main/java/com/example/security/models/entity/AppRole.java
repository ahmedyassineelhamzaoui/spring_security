package com.example.security.models.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppRole implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(unique = true)
    private String authority;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private List<AppPermission> permissions = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppUser> users = new ArrayList<>();

}
