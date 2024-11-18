package com.gruposei.gestion_orquestas.service;

import com.gruposei.gestion_orquestas.model.NewContentType;
import com.gruposei.gestion_orquestas.repositories.NewContentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewContentTypeService {

    @Autowired
    private NewContentTypeRepository newContentTypeRepository;

    public NewContentType create(NewContentType p){

        return newContentTypeRepository.save(p);
    }

    public List<NewContentType> getAll(){

        return newContentTypeRepository.findAll();
    }

    public void delete(NewContentType p){

        newContentTypeRepository.delete(p);
    }

    public void deleteById(Long id){

        newContentTypeRepository.deleteById(id);
    }

    public Optional<NewContentType> findById(Long id){

        return newContentTypeRepository.findById(id);
    }

    public Optional<NewContentType> findBySystemName(String systemName){

        return newContentTypeRepository.findBySystemName(systemName);
    }
}
