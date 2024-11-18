package com.gruposei.gestion_orquestas.service;

import com.gruposei.gestion_orquestas.model.MusicalResourceType;
import com.gruposei.gestion_orquestas.repositories.MusicalResourceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicalResourceTypeService {

    @Autowired
    private MusicalResourceTypeRepository musicalResourceTypeRepository;

    public MusicalResourceType create(MusicalResourceType p){

        return musicalResourceTypeRepository.save(p);
    }

    public List<MusicalResourceType> getAll(){

        return musicalResourceTypeRepository.findAll();
    }

    public void delete(MusicalResourceType p){

        musicalResourceTypeRepository.delete(p);
    }

    public void deleteById(Long id){

        musicalResourceTypeRepository.deleteById(id);
    }

    public Optional<MusicalResourceType> findById(Long id){

        return musicalResourceTypeRepository.findById(id);
    }

    public Optional<MusicalResourceType> findBySystemName(String systemName){

        return musicalResourceTypeRepository.findBySystemName(systemName);
    }
}
