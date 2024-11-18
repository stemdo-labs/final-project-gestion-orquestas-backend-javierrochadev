package com.gruposei.gestion_orquestas.service;

import com.gruposei.gestion_orquestas.model.MyError;
import com.gruposei.gestion_orquestas.repositories.MyErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyErrorService {

    @Autowired
    private MyErrorRepository errorRepository;

    public MyError create(MyError p){

        return errorRepository.save(p);
    }

    public List<MyError> getAll(){

        return errorRepository.findAll();
    }

    public void delete(MyError p){

        errorRepository.delete(p);
    }

    public void deleteById(String id){

        errorRepository.deleteById(id);
    }

    public Optional<MyError> findById(String id){

        return errorRepository.findById(id);
    }
}
