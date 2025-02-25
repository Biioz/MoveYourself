package com.jee.MoveYourself.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/login")
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Serve the login.html page
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // Serve the register.html page (if you have one)
    }
}