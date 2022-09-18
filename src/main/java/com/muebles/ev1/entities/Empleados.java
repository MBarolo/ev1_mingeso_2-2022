package com.muebles.ev1.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Empleados {
    @Id private Long id;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "rut")
    private String rut;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "fecha_ingreso")
    private LocalDate fecha_ingreso;

    @Column(name = "fecha_nac")
    private LocalDate fecha_nac;

}