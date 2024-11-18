package com.gruposei.gestion_orquestas.repositories;

import com.gruposei.gestion_orquestas.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Long> {

    void deleteById(Long id);
}
