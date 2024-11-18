package com.gruposei.gestion_orquestas.repositories;

import com.gruposei.gestion_orquestas.model.MusicalResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicalResourceRepository extends JpaRepository<MusicalResource,Long> {

    void deleteById(Long id);
}
