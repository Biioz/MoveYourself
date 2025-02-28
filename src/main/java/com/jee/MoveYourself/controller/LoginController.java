package com.jee.MoveYourself.controller;

import com.jee.MoveYourself.entities.User;
import com.jee.MoveYourself.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login"; // Serve the login.html page
    }


    @GetMapping("/register")
    public String register() {

        return "register"; // Serve the register.html page (if you have one)
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam int age,
            @RequestParam String gender,
            @RequestParam String pathology) {

        // Create a new User object
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setAge(age);
        newUser.setGender(gender);
        newUser.setPathology(pathology);

        // Save the user to the database

        userService.registerUser(newUser);


        // Redirect to the login page after successful registration
        return "redirect:/login";
    }
}