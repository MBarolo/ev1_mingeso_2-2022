package com.muebles.ev1.services;

import com.muebles.ev1.entities.Empleados;
import com.muebles.ev1.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;
    public Empleados findByRut(String rut){
        return empleadoRepository.findByRut(rut);
    }

    public Optional<Empleados> findById(Long id){
        return empleadoRepository.findById(id);
    }
}
