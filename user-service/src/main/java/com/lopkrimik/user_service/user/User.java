package com.lopkrimik.user_service.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "username", unique = true, nullable = false)
    private String username;

    @Column (name = "email", unique = true, nullable = false)
    private String email;

    @Column (name = "password")
    private String password;

}
