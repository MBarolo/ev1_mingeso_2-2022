package com.muebles.ev1.repositories;

import com.muebles.ev1.entities.EmpleadosPlanilla;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface EmpleadosPlanillasRepository extends CrudRepository<EmpleadosPlanilla, Long> {
    @Query(value= "SELECT e.id, e.rut, e.nombres, e.apellidos, e.fecha_ingreso, e.categoria, p.sfm, p.monto_anyos, " +
            "p.monto_horas, p.cot_prev, p.cot_sal, p.monto_final, p.monto_desc " +
            "FROM empleados AS e, planillas as p " +
            "WHERE e.rut = p.rut_empleado", nativeQuery = true)
    ArrayList<EmpleadosPlanilla> getEmpPlan();
}
