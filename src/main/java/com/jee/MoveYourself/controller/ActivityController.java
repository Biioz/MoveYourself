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

    @PostMapping("/createprogram")
    public String createProgram(@RequestParam String programName) {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(user.getActivities().isEmpty()){
            return "redirect:/my-activities";
        }

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

        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Add the evaluation to the activity
        Evaluation eval = new Evaluation();
        eval.setUser(user);
        eval.setActivity(activity);
        eval.setSatisfactionScore(evaluation);
        evaluationRepository.save(eval);

        return "redirect:/my-activities";
    }

    @PostMapping("/rateAprogram/{programId}")
    public String rateProgram(@PathVariable Long programId, @RequestParam(name = "evaluation2", required = false) Long rate) {
        // Get authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Program program = programService.findProgramById(programId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Add the evaluation to the activity
        program.setRate(rate);
        programService.updateProgram(program);


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

    @PostMapping("/deleteProgram/{programId}")
    public String deleteProgram(@PathVariable Long programId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        programService.deleteProgram(programId);
        return "redirect:/my-activities";
    }
}