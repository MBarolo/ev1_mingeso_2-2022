package com.muebles.ev1.controllers;

import com.muebles.ev1.entities.Marca;
import com.muebles.ev1.services.EmpleadoService;
import com.muebles.ev1.services.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@CrossOrigin
@RestController
public class MarcaController {
    @Autowired
    MarcaService marcaService;
    @Autowired
    EmpleadoService empleadoService;

    @PostMapping("/marcas")
    public Marca insertMarca(@RequestBody Marca m){
        return marcaService.insert(m);
    }
    @GetMapping("/marcas")
    public Iterable<Marca> getMarcas(){
        return marcaService.getAll();
    }
}
