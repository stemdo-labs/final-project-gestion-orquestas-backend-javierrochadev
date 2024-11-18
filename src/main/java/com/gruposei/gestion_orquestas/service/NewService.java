package com.gruposei.gestion_orquestas.service;

import com.gruposei.gestion_orquestas.model.New;
import com.gruposei.gestion_orquestas.model.Role;
import com.gruposei.gestion_orquestas.repositories.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewService {

    @Autowired
    private NewRepository newRepository;

    public New create(New p){

        return newRepository.save(p);
    }

    public List<New> getAll(){

        return newRepository.findAll();
    }

    public void delete(New p){

        newRepository.delete(p);
    }

    public void deleteById(Long id){

        newRepository.deleteById(id);
    }

    public Optional<New> findById(Long id){

        return newRepository.findById(id);
    }
}
