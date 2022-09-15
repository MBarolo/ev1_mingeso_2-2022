package com.muebles.ev1.controllers;

import com.muebles.ev1.entities.Empleados;
import com.muebles.ev1.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@CrossOrigin
@RestController
public class EmpleadoController {
    @Autowired
    EmpleadoRepository empleadoRepository;
    @GetMapping("/empleados")
    public Iterable<Empleados> getAll(){
        return empleadoRepository.findAll();
    }
    @PostMapping("/empleados")
    public Empleados insertEmpleado(@RequestBody Empleados empleados){
        return empleadoRepository.save(empleados);
    }
}
