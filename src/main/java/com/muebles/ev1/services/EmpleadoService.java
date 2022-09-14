package com.muebles.ev1.services;

import com.muebles.ev1.entities.Empleado;
import com.muebles.ev1.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;
    public Empleado findByRut(String rut){
        return empleadoRepository.findByRut(rut);
    }

    public Optional<Empleado> findById(Long id){
        return empleadoRepository.findById(id);
    }
}
