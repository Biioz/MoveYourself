package com.jee.MoveYourself.controller;

import com.jee.MoveYourself.entities.Activity;
import com.jee.MoveYourself.entities.Program;
import com.jee.MoveYourself.entities.User;
import com.jee.MoveYourself.repositories.ActivityRepository;
import com.jee.MoveYourself.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public HomeController(ActivityRepository activityRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/home")
    public String home(
            @RequestParam(name = "query", required = false) String query,
            Model model) {

        // Get authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch activities
        List<Activity> suggestedActivities = activityRepository.findByRecommendedPathology(user.getPathology());
        List<Activity> allActivities;

        if (query != null && !query.isEmpty()) {
            allActivities = activityRepository.findByNameContainingIgnoreCase(query);
        } else {
            allActivities = activityRepository.findAll();
        }

        // Calculate average evaluation for each activity
        for (Activity activity : allActivities) {
            Double averageEvaluation = activityRepository.findAverageEvaluationByActivityId(activity.getId());
            activity.setAverageEvaluation(averageEvaluation != null ? averageEvaluation : 0.0);
        }

        for (Activity activity : suggestedActivities) {
            Double averageEvaluation = activityRepository.findAverageEvaluationByActivityId(activity.getId());
            activity.setAverageEvaluation(averageEvaluation != null ? averageEvaluation : 0.0);
        }

        model.addAttribute("username", user.getUsername());
        model.addAttribute("suggestedActivities", suggestedActivities);
        model.addAttribute("allActivities", allActivities);
        model.addAttribute("query", query);

        return "home";
    }

    @GetMapping("/my-activities")
    public String myActivities(Model model) {
        // Get the authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Fetch the user from the database
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch the user's activities
        List<Activity> userActivities = user.getActivities();

        // Pass data to the Thymeleaf template
        model.addAttribute("username", username);
        model.addAttribute("userActivities", userActivities);

        return "my-activities";
    }


    @PostMapping("/signup/{activityId}")
    public String signUpForActivity(@PathVariable Long activityId) {
        // Get authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get activity
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        // Check if the user is already signed up for this activity
        if (user.getActivities().contains(activity)) {
            // User is already signed up, so redirect with an error message
            return "redirect:/home?error=already_signed_up";
        }

        // Add activity to user's activities
        user.getActivities().add(activity);
        userRepository.save(user);

        return "redirect:/home";
    }
}