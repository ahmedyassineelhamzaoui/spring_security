package com.example.security.models.entity;

import com.example.security.enums.Provider;
import jakarta.persistence.*;
import lombok.*;
import org.aspectj.apache.bcel.classfile.Module;
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

    private String verificationCode;

    private String password;

    private String imageUrl;

    private boolean accountNonExpired=true ;

    private boolean accountNonLocked =true;

    private boolean credentialsNonExpired=true ;

    private boolean enabled =true;

    @Column(name = "provider_id")
    private String providerId;

    @Enumerated(EnumType.STRING)
    private Provider provider;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<AppRole> authorities = new ArrayList<>();

    @Override
    public String getUsername() {
        return email;
    }

}
