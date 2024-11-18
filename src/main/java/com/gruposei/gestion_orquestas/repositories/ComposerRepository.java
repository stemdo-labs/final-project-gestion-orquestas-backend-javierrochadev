package com.gruposei.gestion_orquestas.repositories;

import com.gruposei.gestion_orquestas.model.Composer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComposerRepository extends JpaRepository<Composer,Long> {

    void deleteById(Long id);
}
