package com.gruposei.gestion_orquestas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gruposei.gestion_orquestas.model.PaymentRequest;
import com.gruposei.gestion_orquestas.repositories.PaymentRequestRepository;

@Service
public class PaymentRequestService {

    @Autowired
    private PaymentRequestRepository paymentRequestRepository;

    public List<PaymentRequest> getAll(){

        return paymentRequestRepository.findAll();
    }

    public void delete(PaymentRequest p){

        paymentRequestRepository.delete(p);
    }

    public Optional<PaymentRequest> findById(Long id){

        return paymentRequestRepository.findById(id);
    }

    public Optional<PaymentRequest> findByExternalReference(String er){

        return paymentRequestRepository.findByExternalReference(er);
    }

    public boolean existsByExternalReference(String er){

        return paymentRequestRepository.existsByExternalReference(er);
    }

    public PaymentRequest create(PaymentRequest p){

        if(p.getId() == null){

            p = paymentRequestRepository.save(p);
            p.setExternalReference(p.getId().toString() + p.getShow().getId().toString() + p.getQuantity() + p.getUser().getUsername());
        }

        return paymentRequestRepository.save(p);
    }
}
