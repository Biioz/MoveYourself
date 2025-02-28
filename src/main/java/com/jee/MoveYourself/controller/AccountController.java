package com.jee.MoveYourself.controller;

import com.jee.MoveYourself.entities.User;
import com.jee.MoveYourself.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    private final UserRepository userRepository;

    public AccountController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/account")
    public String account(Model model) {
        // Get the authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Fetch the user from the database
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Pass the user's data to the Thymeleaf template
        model.addAttribute("username", user.getUsername());
        model.addAttribute("pathology", user.getPathology());

        return "account";
    }

    @PostMapping("/update-account")
    public String updateAccount(
            @RequestParam String pathology) {
        // Get the authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Fetch the user from the database
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update the user's data
        user.setPathology(pathology);

        // Save the updated user to the database
        userRepository.save(user);

        // Redirect back to the account page
        return "redirect:/account";
    }
}