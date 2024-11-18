package com.gruposei.gestion_orquestas.repositories;

import com.gruposei.gestion_orquestas.model.MusicalResourceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MusicalResourceTypeRepository extends JpaRepository<MusicalResourceType,Long> {

    Optional<MusicalResourceType> findBySystemName(String systemName);

    void deleteById(Long id);
}
