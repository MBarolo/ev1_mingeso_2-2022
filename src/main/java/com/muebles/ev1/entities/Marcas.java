package com.muebles.ev1.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Marcas {
    @Id @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Column(name = "rut_empleado")
    private String rut_empleado;

    @Column(name = "justificativo")
    private Boolean justificativo;

    @Column(name = "autorizacion")
    private Boolean autorizacion;
}