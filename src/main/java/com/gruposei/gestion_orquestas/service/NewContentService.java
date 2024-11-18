package com.gruposei.gestion_orquestas.service;

import com.gruposei.gestion_orquestas.model.NewContent;
import com.gruposei.gestion_orquestas.repositories.NewContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewContentService {

    @Autowired
    private NewContentRepository newContentRepository;

    public NewContent create(NewContent p){

        return newContentRepository.save(p);
    }

    public List<NewContent> getAll(){

        return newContentRepository.findAll();
    }

    public void delete(NewContent p){

        newContentRepository.delete(p);
    }

    public void deleteById(Long id){

        newContentRepository.deleteById(id);
    }

    public Optional<NewContent> findById(Long id){

        return newContentRepository.findById(id);
    }
}
