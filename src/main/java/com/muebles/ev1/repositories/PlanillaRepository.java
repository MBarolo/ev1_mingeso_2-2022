package com.muebles.ev1.repositories;

import com.muebles.ev1.entities.Planillas;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PlanillaRepository extends CrudRepository<Planillas, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO planillas (rut_empleado, sfm, monto_anyos, monto_horas, monto_desc, monto_final, " +
            "cot_prev, cot_sal) VALUES (:rut_empleado, :sfm, :monto_anyos, :monto_horas, :monto_desc, :monto_final, "+
            ":cot_prev, :cot_sal)", nativeQuery = true)
    public void insertPlanilla(@Param("rut_empleado") String rut, @Param("sfm") int sfm,
                               @Param("monto_anyos") int monto_anyos, @Param("monto_horas") int monto_horas,
                               @Param("monto_desc") int monto_desc, @Param("monto_final") int monto_final,
                               @Param("cot_prev") int cot_prev, @Param("cot_sal") int cot_sal);
    @Modifying
    @Transactional
    @Query(value = "DELETE * FROM planillas")
    public void deleteAllPlanillas();
}
