package com.jee.MoveYourself.controller;



import com.jee.MoveYourself.entities.Activity;
import com.jee.MoveYourself.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    // Add a new activity
    @PostMapping
    public ResponseEntity<Activity> addActivity(@RequestBody Activity activity) {
        Activity newActivity = activityService.addActivity(activity);
        return ResponseEntity.ok(newActivity);
    }

    // Get an activity by ID
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        Optional<Activity> activity = activityService.findActivityById(id);
        return activity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get activities by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Activity>> getActivitiesByCategory(@PathVariable String category) {
        List<Activity> activities = activityService.findActivitiesByCategory(category);
        return ResponseEntity.ok(activities);
    }

    // Get activities by recommended pathology
    @GetMapping("/pathology/{pathology}")
    public ResponseEntity<List<Activity>> getActivitiesByPathology(@PathVariable String pathology) {
        List<Activity> activities = activityService.findActivitiesByRecommendedPathology(pathology);
        return ResponseEntity.ok(activities);
    }

    // Search activities by name
    @GetMapping("/search")
    public ResponseEntity<List<Activity>> searchActivitiesByName(@RequestParam String name) {
        List<Activity> activities = activityService.searchActivitiesByName(name);
        return ResponseEntity.ok(activities);
    }

    // Update an activity
    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
        activity.setId(id); // Ensure the ID is set
        Activity updatedActivity = activityService.updateActivity(activity);
        return ResponseEntity.ok(updatedActivity);
    }

    // Delete an activity by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }

    // Get all activities
    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {
        List<Activity> activities = activityService.getAllActivities();
        return ResponseEntity.ok(activities);
    }
}