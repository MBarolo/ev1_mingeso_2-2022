package com.muebles.ev1.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Planillas {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="rut_empleado")
    private String rut_empleado;

    @Column(name = "sfm", nullable = false)
    private Integer sfm;

    @Column(name = "monto_anyos", nullable = false)
    private Integer monto_anyos;

    @Column(name = "monto_horas", nullable = false)
    private Integer monto_horas;

    @Column(name = "monto_desc", nullable = false)
    private Integer monto_desc;

    @Column(name = "monto_final", nullable = false)
    private Integer monto_final;

    @Column(name = "cot_prev", nullable = false)
    private Integer cot_prev;

    @Column(name = "cot_sal", nullable = false)
    private Integer cot_sal;
}