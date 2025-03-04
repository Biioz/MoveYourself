package com.jee.MoveYourself.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String hellowword(){
        return "redirect:/login";
    }
}
