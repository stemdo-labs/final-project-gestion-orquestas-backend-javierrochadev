package com.gruposei.gestion_orquestas.repositories;

import com.gruposei.gestion_orquestas.model.MeetingMinute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingMinuteRepository extends JpaRepository<MeetingMinute,Long> {

    void deleteById(Long id);
}
