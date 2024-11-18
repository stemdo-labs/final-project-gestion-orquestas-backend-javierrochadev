package com.gruposei.gestion_orquestas.repositories;

import com.gruposei.gestion_orquestas.model.NewContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewContentRepository extends JpaRepository<NewContent,Long> {

    void deleteById(Long id);
}
