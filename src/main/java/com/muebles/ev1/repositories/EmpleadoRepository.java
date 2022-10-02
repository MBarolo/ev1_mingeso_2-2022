package com.muebles.ev1.repositories;

import com.muebles.ev1.entities.Empleados;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleados, Long> {
    @Query(value="SELECT * FROM empleados as e WHERE e.rut = ?1", nativeQuery=true)
    public Empleados findByRut(String rut);
    @Query(value ="SELECT rut FROM empleados", nativeQuery = true)
    public ArrayList<String> getRuts();

    @Modifying
    @Transactional
    @Query(value="INSERT INTO empleados (rut, apellidos, nombres, categoria, fecha_ingreso, fecha_nac)" +
            " VALUES (:rut, :apellidos, :nombres, :categoria, :fecha_ingreso, :fecha_nac)",nativeQuery = true)
    void insertEmpleado(
            @Param("rut") String rut,
            @Param("apellidos") String apellidos,
            @Param("nombres") String nombres,
            @Param("categoria") String categoria,
            @Param("fecha_ingreso") LocalDate fecha_ingreso,
            @Param("fecha_nac") LocalDate fecha_nac
    );
}
