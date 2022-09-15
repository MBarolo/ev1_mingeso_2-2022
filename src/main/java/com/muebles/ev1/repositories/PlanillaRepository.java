package com.muebles.ev1.repositories;

import com.muebles.ev1.entities.Planillas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanillaRepository extends CrudRepository<Planillas, Long> {
}
