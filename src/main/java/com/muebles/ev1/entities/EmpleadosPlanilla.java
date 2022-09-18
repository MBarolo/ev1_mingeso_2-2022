package com.muebles.ev1.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class EmpleadosPlanilla {
    @Id
    Long id;
    String rut;
    String nombres;
    String apellidos;
    LocalDate fecha_ingreso;
    String categoria;
    Integer sfm;
    Integer monto_anyos;
    Integer monto_horas;
    Integer monto_desc;
    Integer monto_final;
    Integer cot_prev;
    Integer cot_sal;
}
