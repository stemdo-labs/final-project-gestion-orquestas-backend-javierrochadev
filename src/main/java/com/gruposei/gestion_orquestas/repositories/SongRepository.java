package com.gruposei.gestion_orquestas.repositories;

import com.gruposei.gestion_orquestas.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song,Long> {

    void deleteById(Long id);
}
