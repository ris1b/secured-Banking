package com.ris1b.springSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class Welcome {

    @GetMapping("/welcome")
    public String greeting(){
        return "Welcome bla bla";
    }
}
