package com.gruposei.gestion_orquestas.repositories;

import com.gruposei.gestion_orquestas.model.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest,Long> {

    Optional<PaymentRequest> findByExternalReference(String externalReference);

    boolean existsByExternalReference(String externalReference);
}
