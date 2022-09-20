package com.muebles.ev1.services;

import com.muebles.ev1.entities.Empleados;
import com.muebles.ev1.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static java.lang.Math.max;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;
    public Empleados findByRut(String rut){
        return empleadoRepository.findByRut(rut);
    }
    public ArrayList<String> getRuts(){return empleadoRepository.getRuts();}
    public int calculateSFM(String cat){
        return switch (cat) {
            case "a" -> 1700000;
            case "b" -> 1200000;
            case "c" -> 800000;
            default -> 0;
        };
    }
    public int getValueOvertime(String cat){
        return switch (cat) {
            case "a" -> 25000;
            case "b" -> 20000;
            case "c" -> 10000;
            default -> 0;
        };
    }
    public Optional<Empleados> findById(Long id){
        return empleadoRepository.findById(id);
    }

    public int getAnyosServicio(String rut, int anyo) {
        Empleados empleado = empleadoRepository.findByRut(rut);
        return max(anyo-empleado.getFecha_ingreso().getYear(), 0);
    }
}
