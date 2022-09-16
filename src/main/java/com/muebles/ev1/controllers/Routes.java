package com.muebles.ev1.controllers;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping
public class Routes {
    @GetMapping("/home")
    public String home(){
        return "index";
    }
    @GetMapping("/clock")
    public String clock(){return "clock";}
    @GetMapping("/authorize")
    public String authorize(){return "authorize";}
}
