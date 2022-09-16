package com.muebles.ev1.controllers;

import com.muebles.ev1.entities.Marcas;
import com.muebles.ev1.services.EmpleadoService;
import com.muebles.ev1.services.MarcaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

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
    public int insertMarca(@RequestBody Marcas m){
        marcaService.insertMarca(m);
        return 1;
    }
    @GetMapping("/marcas")
    public Iterable<Marcas> getMarcas(){
        return marcaService.getAll();
    }

    @PostMapping("/marcas/authorize")
    public String authorizeMarca(@RequestParam("fecha") String date, @RequestParam ("rut") String rut){
        if(rut.length() == 12) {
            marcaService.authorizeMarca(LocalDate.parse(date), rut);
            return "Horas extra autorizadas correctamente.";
        }
        else
            return "Rut mal ingresado. Ingresar con el formato indicado.";
    }

    @RequestMapping(value="/marcas/from_file", headers = "Content-Type=multipart/form-data", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public String insertMarcaFromFile(@RequestParam("archivo") MultipartFile file){
        if(!file.isEmpty()){
            try{
                InputStream stream = file.getInputStream();
                Scanner scan = new Scanner(stream);
                String[] args;
                String x;
                Marcas m = new Marcas();
                m.setAutorizacion(false);
                m.setJustificativo(false);
                while(scan.hasNextLine()){
                    x = scan.nextLine();
                    args = x.split(";");
                    args[0] = args[0].replaceAll("/", "-");
                    m.setFecha(LocalDate.parse(args[0]));
                    m.setHora(LocalTime.parse(args[1]));
                    m.setRut_empleado(args[2]);
                    marcaService.insertMarca(m);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
            return "Marcas añadidas exitosamente.";
        }
        else return "Error al añadir las marcas. Archivo inexistente o con formato inadecuado.";
    }
}
