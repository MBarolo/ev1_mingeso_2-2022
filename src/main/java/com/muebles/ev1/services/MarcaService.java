package com.muebles.ev1.services;

import com.muebles.ev1.entities.Marca;
import com.muebles.ev1.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class MarcaService {
    @Autowired
    MarcaRepository marcaRepository;
    public Optional<Marca> getById(Long id){
        return marcaRepository.findById(id);
    }
    public Iterable<Marca> getAll(){
        return marcaRepository.findAll();
    }
    public ArrayList<Marca> getAllByEmpleadoRut(String rut){
        return marcaRepository.findAllByEmpleadoRut(rut);
    }

    public Marca insert(Marca marca){
        return marcaRepository.save(marca);
    }
}
