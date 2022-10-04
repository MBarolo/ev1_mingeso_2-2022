package com.muebles.ev1.controllers;

import com.muebles.ev1.services.PlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@RequestMapping
@CrossOrigin
@RestController
public class PlanillaController {
    @Autowired
    PlanillaService planillaService;
    @PostMapping("/planillas/generate")
    public ModelAndView generarPlanilla(@RequestParam("mes") int mes, @RequestParam ("anyo") int anyo, Model model){
        planillaService.delete();
        planillaService.generate(mes, anyo);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view");
        modelAndView.getModel().put("emp_plan", planillaService.getEmpPlan());
        modelAndView.getModel().put("date",LocalDate.of(anyo,mes,1));
        return modelAndView;
    }
}
