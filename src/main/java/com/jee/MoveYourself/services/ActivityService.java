/*package com.jee.MoveYourself.services;



import com.jee.MoveYourself.entities.Activity;
import com.jee.MoveYourself.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    // Add a new activity
    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    // Find an activity by ID
    public Optional<Activity> findActivityById(Long id) {
        return activityRepository.findById(id);
    }

    // Find activities by category
    public List<Activity> findActivitiesByCategory(String category) {
        return activityRepository.findByCategory(category);
    }

    // Find activities by recommended pathology
    public List<Activity> findActivitiesByRecommendedPathology(String pathology) {
        return activityRepository.findByRecommendedPathology(pathology);
    }

    // Search activities by name (case-insensitive)
    public List<Activity> searchActivitiesByName(String name) {
        return activityRepository.findByNameContainingIgnoreCase(name);
    }

    // Update an activity
    public Activity updateActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    // Delete an activity by ID
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    // List all activities
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }
}*/