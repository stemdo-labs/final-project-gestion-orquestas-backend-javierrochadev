package com.gruposei.gestion_orquestas.service;

import com.gruposei.gestion_orquestas.model.Song;
import com.gruposei.gestion_orquestas.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Song create(Song p){

        return songRepository.save(p);
    }

    public List<Song> getAll(){

        return songRepository.findAll();
    }

    public void delete(Song p){

        songRepository.delete(p);
    }

    public void deleteById(Long id){

        songRepository.deleteById(id);
    }

    public Optional<Song> findById(Long id){

        return songRepository.findById(id);
    }
}
