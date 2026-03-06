package ru.kpfu.itis.sorokin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
