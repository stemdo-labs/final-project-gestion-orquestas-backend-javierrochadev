package com.gruposei.gestion_orquestas.service;

import com.gruposei.gestion_orquestas.model.MusicalResource;
import com.gruposei.gestion_orquestas.repositories.MusicalResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicalResourceService {

    @Autowired
    private MusicalResourceRepository musicalResourceRepository;

    public MusicalResource create(MusicalResource p){

        return musicalResourceRepository.save(p);
    }

    public List<MusicalResource> getAll(){

        return musicalResourceRepository.findAll();
    }

    public void delete(MusicalResource p){

        musicalResourceRepository.delete(p);
    }

    public void deleteById(Long id){

        musicalResourceRepository.deleteById(id);
    }

    public Optional<MusicalResource> findById(Long id){

        return musicalResourceRepository.findById(id);
    }
}
