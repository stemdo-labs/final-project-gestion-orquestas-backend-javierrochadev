package com.gruposei.gestion_orquestas.repositories;

import com.gruposei.gestion_orquestas.model.MyError;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyErrorRepository extends JpaRepository<MyError,String> {

    Optional<MyError> findById(String id);
}
