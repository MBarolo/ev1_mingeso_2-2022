package com.muebles.ev1.repositories;

import com.muebles.ev1.entities.Empleados;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleados, Long> {
    @Query(value="SELECT * FROM empleados as e WHERE e.rut = ?1", nativeQuery=true)
    public Empleados findByRut(String rut);
}