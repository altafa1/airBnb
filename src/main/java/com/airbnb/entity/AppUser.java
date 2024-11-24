package com.airbnb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "user_name", nullable = false, unique = true, length = 35)
    private String userName;

    @Column(name = "email", nullable = false, unique = true, length = 128)
    private String email;


    @Column(name = "password", nullable = false, length = 500)
    @JsonIgnore
    private String password;

    @JsonIgnore
    private String role;

}