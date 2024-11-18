package com.gruposei.gestion_orquestas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gruposei.gestion_orquestas.model.MeetingMinute;
import com.gruposei.gestion_orquestas.repositories.MeetingMinuteRepository;

@Service
public class MeetingMinuteService {

    @Autowired
    private MeetingMinuteRepository meetingMinuteRepository;

    public MeetingMinute create(MeetingMinute p){

        return meetingMinuteRepository.save(p);
    }

    public List<MeetingMinute> getAll(){

        return meetingMinuteRepository.findAll();
    }

    public void delete(MeetingMinute p){

        meetingMinuteRepository.delete(p);
    }

    public void deleteById(Long id){

        meetingMinuteRepository.deleteById(id);
    }

    public Optional<MeetingMinute> findById(Long id){

        return meetingMinuteRepository.findById(id);
    }
}
