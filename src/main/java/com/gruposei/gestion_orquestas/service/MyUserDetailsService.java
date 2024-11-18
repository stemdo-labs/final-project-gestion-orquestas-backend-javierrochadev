package com.gruposei.gestion_orquestas.service;

import com.gruposei.gestion_orquestas.model.MyUserDetails;
import com.gruposei.gestion_orquestas.model.User;
import com.gruposei.gestion_orquestas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
//        return user.map(MyUserDetails::new).get();
        //return new User("admin","admin",new ArrayList<>());
        return new MyUserDetails(user.get());
    }
}
