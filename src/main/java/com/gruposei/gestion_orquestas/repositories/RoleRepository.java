package com.gruposei.gestion_orquestas.repositories;

import com.gruposei.gestion_orquestas.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);

    void deleteById(Long id);
}
