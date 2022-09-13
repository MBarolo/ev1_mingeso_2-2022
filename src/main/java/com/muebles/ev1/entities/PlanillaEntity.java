package com.muebles.ev1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "planillas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanillaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "rut", referencedColumnName = "rut")
    private EmpleadoEntity rut;

    @Column(nullable = false)
    private int sfm;

    @Column(nullable = false)
    private int monto_anyos;

    @Column(nullable = false)
    private int monto_horas;

    @Column(nullable = false)
    private int monto_desc;

    @Column(nullable = false)
    private int monto_final;

    @Column(nullable = false)
    private int cot_prev;

    @Column(nullable = false)
    private int cot_sal;
}
