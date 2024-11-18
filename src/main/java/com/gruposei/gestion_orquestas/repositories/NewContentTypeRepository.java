package com.gruposei.gestion_orquestas.repositories;

import com.gruposei.gestion_orquestas.model.MusicalResourceType;
import com.gruposei.gestion_orquestas.model.NewContentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewContentTypeRepository extends JpaRepository<NewContentType,Long> {

    Optional<NewContentType> findBySystemName(String systemName);

    void deleteById(Long id);
}
