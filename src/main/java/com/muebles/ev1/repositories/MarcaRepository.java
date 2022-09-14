package com.muebles.ev1.repositories;

import com.muebles.ev1.entities.Marca;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MarcaRepository extends CrudRepository<Marca, Long> {
    @Query(value = "SELECT m FROM marcas as m WHERE m.rut = ?1", nativeQuery = true)
    public ArrayList<Marca> findAllByEmpleadoRut(String rut);
}
