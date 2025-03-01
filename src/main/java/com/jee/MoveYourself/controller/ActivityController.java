package com.jee.MoveYourself.controller;



import com.jee.MoveYourself.entities.Activity;
import com.jee.MoveYourself.entities.Evaluation;
import com.jee.MoveYourself.entities.Program;
import com.jee.MoveYourself.entities.User;
import com.jee.MoveYourself.repositories.ActivityRepository;
import com.jee.MoveYourself.repositories.EvaluationRepository;
import com.jee.MoveYourself.repositories.ProgramRepository;
import com.jee.MoveYourself.repositories.UserActivityRepository;
import com.jee.MoveYourself.repositories.UserRepository;
import com.jee.MoveYourself.services.ActivityService;
import com.jee.MoveYourself.services.ProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping("/api/my-activities")
@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ProgramService programService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private UserActivityRepository userActivityRepository;
/*
    // Add a new activity
    @PostMapping
    public ResponseEntity<Activity> addActivity(@RequestBody Activity activity) {
        Activity newActivity = activityService.addActivity(activity);
        return ResponseEntity.ok(newActivity);
    }*/

    @PostMapping("/createprogram")
    public String createProgram(@RequestParam String programName) {
        // Get the authenticated user
        System.out.println("njiebfjinebfnji");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a new program
        Program program = new Program();
        program.setName(programName); // You can customize the name
        program.setUser(user);

        // Add the user's subscribed activities to the program
        List<Activity> programActivities = new ArrayList<>(user.getActivities());
        program.setActivities(programActivities);

        // Save the program (this will also save the links in the program_activity table)
        programService.createProgram(program);

        return "redirect:/my-activities"; // Redirect to the programs page
    }

    @PostMapping("/rate/{activityId}")
    public String rateActivity(@PathVariable Long activityId, @RequestParam Integer evaluation) {
        // Get authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Add the evaluation to the activity
        Evaluation eval = new Evaluation();
        eval.setId(activityId);
        eval.setUser(user);
        eval.setSatisfactionScore(evaluation);
        evaluationRepository.save(eval);

        return "redirect:/my-activities";
    }

    @PostMapping("/signout/{activityId}")
    public String signoutAcvtivity(@PathVariable Long activityId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userActivityRepository.deleteByUserIdAndActivityId(user.getId(), activityId);
        return "redirect:/my-activities";
    }

    @PostMapping("/deleteProgram/{ProgramId}")
    public String deleteProgram(@PathVariable Long programId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        programService.deleteProgram(programId);
        return "redirect:/my-activities";
    }
/*
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
    }*/
}