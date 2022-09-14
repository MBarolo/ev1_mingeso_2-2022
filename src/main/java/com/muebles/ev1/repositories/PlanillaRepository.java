package com.muebles.ev1.repositories;

import com.muebles.ev1.entities.PlanillaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanillaRepository extends CrudRepository<PlanillaEntity, Long> {
}
