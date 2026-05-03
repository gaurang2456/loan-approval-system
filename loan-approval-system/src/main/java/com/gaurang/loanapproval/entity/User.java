package com.gaurang.loanapproval.entity;

import com.gaurang.loanapproval.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Getter
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;

    public void setPassword(@Nullable String encode) {
    }

    public void setRole(Role role) {
    }

    public void setName(String name) {
    }

    public void setEmail(String email) {
    }

    public void setCreatedAt(LocalDateTime now) {
    }

}
