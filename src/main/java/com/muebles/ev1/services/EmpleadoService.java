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
        switch (cat){
            case "a":
                return 1700000;
            case "b":
                return 1200000;
            case "c":
                return 800000;
        }
        return 0;
    }
    public int getValueOvertime(String cat){
        switch(cat){
            case "a":
                return 25000;
            case "b":
                return 20000;
            case "c":
                return 10000;
        }
        return 0;
    }
    public Optional<Empleados> findById(Long id){
        return empleadoRepository.findById(id);
    }

    public int getAnyosServicio(String rut, int anyo) {
        Empleados empleado = empleadoRepository.findByRut(rut);
        return max(anyo-empleado.getFecha_ingreso().getYear(), 0);
    }
}
