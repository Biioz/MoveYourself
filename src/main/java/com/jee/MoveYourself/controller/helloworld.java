package com.jee.MoveYourself.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloworld {

    @GetMapping("/")
    public String hellowword(){
        return " oui oui c'est ça ma grosse bite";
    }
}
