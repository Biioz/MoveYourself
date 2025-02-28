package com.jee.MoveYourself.entities;

import jakarta.persistence.*;

@Entity
public class Pathology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Default constructor (required by JPA)
    public Pathology() {}

    // Parameterized constructor
    public Pathology(String name) {
        this.name = name;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // toString method (optional, for debugging)
    @Override
    public String toString() {
        return "Pathology{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}