package com.muebles.ev1.services;

import com.muebles.ev1.entities.Marcas;
import com.muebles.ev1.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class MarcaService {
    @Autowired
    MarcaRepository marcaRepository;
    public Optional<Marcas> getById(Long id){
        return marcaRepository.findById(id);
    }
    public Iterable<Marcas> getAll(){
        return marcaRepository.findAll();
    }
    public ArrayList<Marcas> getAllByEmpleadoRut(String rut){
        return marcaRepository.findAllByEmpleadoRut(rut);
    }

    public void insertMarca(Marcas marcas){
        marcaRepository.insertMarca(marcas.getFecha(), marcas.getHora(), marcas.getRut_empleado(),
                marcas.getJustificativo(), marcas.getAutorizacion());
    }

    public void authorizeMarca(LocalDate date, String rut){
        marcaRepository.authorizeMarca(date, rut);
    }
    public void justifyMarca(LocalDate date, String rut){
        marcaRepository.justifyMarca(date, rut);
    }

    public ArrayList<Marcas> getByMonthAndRut(int mes, int anyo, String rut) {
       return marcaRepository.getByMonthAndRut(mes, anyo, rut);
    }

    public ArrayList<Marcas> getByDayAndRut(LocalDate date, String rut) {
        return marcaRepository.getByDayAndRut(date, rut);
    }
}
