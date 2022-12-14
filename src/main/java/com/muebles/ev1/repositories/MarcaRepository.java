package com.muebles.ev1.repositories;

import com.muebles.ev1.entities.Marcas;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Repository
public interface MarcaRepository extends CrudRepository<Marcas, Long> {
    @Query(value = "SELECT m FROM marcas as m WHERE m.rut = ?1", nativeQuery = true)
    public ArrayList<Marcas> findAllByEmpleadoRut(String rut);
    @Query(value ="SELECT * FROM marcas as m WHERE MONTH(m.fecha) = :mes AND YEAR(m.fecha) = :anyo AND m.rut_empleado = :rut", nativeQuery = true)
    public ArrayList<Marcas> getByMonthAndRut(@Param("mes")int mes, @Param("anyo") int anyo, @Param("rut") String rut);
    @Query(value = "SELECT * FROM marcas as m WHERE DAY(m.fecha) = DAY(:fecha) AND " +
            "MONTH(m.fecha) = MONTH(:fecha) AND YEAR(m.fecha) = YEAR(:fecha) AND m.rut_empleado = :rut " +
            "ORDER BY m.hora",nativeQuery = true)
    public ArrayList<Marcas> getByDayAndRut(@Param("fecha") LocalDate date, @Param("rut") String rut);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO marcas (fecha, hora, rut_empleado, justificativo, autorizacion)" +
            " VALUES (:fecha,:hora,:rut_empleado,:justificativo,:autorizacion)", nativeQuery = true)
    public void insertMarca(@Param("fecha")LocalDate fecha, @Param("hora") LocalTime hora,
                            @Param("rut_empleado")String rut_empleado, @Param("justificativo")Boolean justificativo,
                            @Param("autorizacion") Boolean autorizacion);

    @Modifying
    @Transactional
    @Query(value = "UPDATE marcas " +
            "SET autorizacion = 1 " +
            "WHERE fecha = :date and rut_empleado = :rut", nativeQuery = true)
    public void authorizeMarca(@Param("date") LocalDate date, @Param("rut") String rut);

    @Modifying
    @Transactional
    @Query(value = "UPDATE marcas " +
            "SET justificativo = 1 " +
            "WHERE fecha = :date and rut_empleado = :rut", nativeQuery = true)
    public void justifyMarca(@Param("date") LocalDate date, @Param("rut") String rut);
}
