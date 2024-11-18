package com.gruposei.gestion_orquestas.service;

import com.gruposei.gestion_orquestas.model.Show;
import com.gruposei.gestion_orquestas.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    public Show create(Show p){

        return showRepository.save(p);
    }

    public List<Show> getAll(){

        return showRepository.findAll();
    }

    public void delete(Show p){

        showRepository.delete(p);
    }

    public void deleteById(Long id){

        showRepository.deleteById(id);
    }

    public Optional<Show> findById(Long id){

        return showRepository.findById(id);
    }
}
