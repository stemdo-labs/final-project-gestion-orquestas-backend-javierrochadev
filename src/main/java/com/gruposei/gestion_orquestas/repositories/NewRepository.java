package com.gruposei.gestion_orquestas.repositories;

import com.gruposei.gestion_orquestas.model.New;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewRepository extends JpaRepository<New,Long> {

    void deleteById(Long id);
}
