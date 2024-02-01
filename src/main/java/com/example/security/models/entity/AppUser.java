package com.example.security.models.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean accountNonExpired=true ;

    private boolean accountNonLocked =true;

    private boolean credentialsNonExpired=true ;

    private boolean enabled =true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<AppRole> authorities = new ArrayList<>();

    @Override
    public String getUsername() {
        return email;
    }

}
