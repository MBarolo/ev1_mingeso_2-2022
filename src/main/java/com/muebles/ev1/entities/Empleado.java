package com.muebles.ev1.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Empleado {
    @Id private Long id;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "fecha_ingreso")
    private LocalDate fecha_ingreso;

    @Column(name = "fecha_nac")
    private LocalDate fecha_nac;

}