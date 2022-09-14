package com.muebles.ev1.services;

import com.muebles.ev1.entities.MarcaEntity;
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
    public Optional<MarcaEntity> getById(Long id){
        return marcaRepository.findById(id);
    }
    public Iterable<MarcaEntity> getAll(){
        return marcaRepository.findAll();
    }
    public ArrayList<MarcaEntity> getAllByEmpleadoRut(String rut){
        return marcaRepository.findAllByEmpleadoRut(rut);
    }

    public MarcaEntity insert(MarcaEntity marca){
        return marcaRepository.save(marca);
    }
}
