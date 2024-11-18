package com.gruposei.gestion_orquestas.service;

import com.gruposei.gestion_orquestas.model.User;
import com.gruposei.gestion_orquestas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User p){

        return userRepository.save(p);
    }

    public List<User> getAllUsers(){

        return userRepository.findAll();
    }

    public void delete(User p){

        userRepository.delete(p);
    }

    public void deleteById(Long id){

        userRepository.deleteById(id);
    }

    public void deleteByUsername(String username){

        userRepository.deleteByUsername(username);
    }

    public Optional<User> findById(Long id){

        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username){

        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email){

        return userRepository.findByEmail(email);
    }

    public boolean existsByUsername(String username){

        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){

        return userRepository.existsByEmail(email);
    }

    public boolean existsById(Long id){

        return userRepository.existsById(id);
    }
}
