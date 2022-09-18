package com.muebles.ev1.controllers;

import com.muebles.ev1.entities.Planillas;
import com.muebles.ev1.services.PlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.ArrayList;

@Controller
@RequestMapping
public class Routes {
    @Autowired
    PlanillaService planillaService;
    @GetMapping("/home")
    public String home(){
        return "index";
    }
    @GetMapping("/clock")
    public String clock(){return "clock";}
    @GetMapping("/authorize")
    public String authorize(){return "authorize";}

    @GetMapping("/justify")
    public String justify(){return "justify";}

    @GetMapping("/generate")
    public String generate(){
        return "generate";
    }
    @GetMapping("/view")
    public String view(Model model){
        return "view";
    }

}
