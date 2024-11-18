package com.gruposei.gestion_orquestas.service;

import com.gruposei.gestion_orquestas.model.Role;
import com.gruposei.gestion_orquestas.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role create(Role p){

        return roleRepository.save(p);
    }

    public List<Role> getAll(){

        return roleRepository.findAll();
    }

    public void delete(Role p){

        roleRepository.delete(p);
    }

    public void deleteById(Long id){

        roleRepository.deleteById(id);
    }

    public Optional<Role> findById(Long id){

        return roleRepository.findById(id);
    }

    public Optional<Role> findByName(String name){

        return roleRepository.findByName(name);
    }
}
