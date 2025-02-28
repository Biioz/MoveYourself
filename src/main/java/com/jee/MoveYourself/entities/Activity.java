package com.jee.MoveYourself.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String category;
    private String recommendedPathology; // Pathologie recommandée pour cette activité

    @ManyToMany(mappedBy = "activities")
    private List<User> users = new ArrayList<>();

    // Constructors, getters, and setters
    public Activity() {}

    public Activity(String name, String description, String category, String recommendedPathology) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.recommendedPathology = recommendedPathology;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRecommendedPathology() {
        return recommendedPathology;
    }

    public void setRecommendedPathology(String recommendedPathology) {
        this.recommendedPathology = recommendedPathology;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}