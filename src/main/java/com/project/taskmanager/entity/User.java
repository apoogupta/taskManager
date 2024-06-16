package com.project.taskmanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity @Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;

    @Column(length = 60) // Consider appropriate length based on your hashing strategy
    private String password; // Hashed password, not plain text!

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
// Getters, setters, and other relevant methods
}