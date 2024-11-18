package com.gruposei.gestion_orquestas.repositories;

import com.gruposei.gestion_orquestas.model.Show;
import com.gruposei.gestion_orquestas.model.Ticket;
import com.gruposei.gestion_orquestas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    void deleteById(Long id);
    void deleteByCode(String code);
    void deleteByUserAndShow(User user, Show show);

    List<Optional<Ticket>> findByUser(User user);
    List<Optional<Ticket>> findByShow(Show show);
    Optional<Ticket> findByCode(String code);
}
