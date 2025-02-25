package com.jee.MoveYourself.repositories;


import com.jee.MoveYourself.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    // Find activities by category
    List<Activity> findByCategory(String category);

    // Find activities by recommended pathology
    List<Activity> findByRecommendedPathology(String recommendedPathology);

    // Find activities by name (case-insensitive search)
    List<Activity> findByNameContainingIgnoreCase(String name);
}
