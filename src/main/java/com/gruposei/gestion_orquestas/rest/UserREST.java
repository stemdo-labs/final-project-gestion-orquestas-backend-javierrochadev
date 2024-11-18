package com.gruposei.gestion_orquestas.rest;

import com.gruposei.gestion_orquestas.model.Role;
import com.gruposei.gestion_orquestas.model.User;
import com.gruposei.gestion_orquestas.responses.ApiRequestException;
import com.gruposei.gestion_orquestas.responses.ResponseHandler;
import com.gruposei.gestion_orquestas.service.RoleService;
import com.gruposei.gestion_orquestas.service.UserService;
import com.gruposei.gestion_orquestas.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserREST {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ResponseHandler responseHandler;

    @PostMapping
    private ResponseEntity<Object> saveUser(@RequestBody User p){

        if(p.getId() == null){

            if (userService.existsByUsername(p.getUsername())){

                throw  new ApiRequestException("003");
            }

            if (userService.existsByEmail(p.getEmail())){

                throw  new ApiRequestException("004");
            }
        }

        if(!Util.validEmail(p.getEmail())){

            throw  new ApiRequestException("006");
        }

        try{

            String encodedPassword = bCryptPasswordEncoder.encode(p.getPassword());
            p.setPassword(encodedPassword);
            User temporal = userService.create(p);

            return responseHandler.generateResponse("000",temporal);
        }
        catch(Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @PostMapping(value = "/register")
    private ResponseEntity<Object> saveGuestUser(@RequestBody User p){

        if(p.getId() == null){

            if (userService.existsByUsername(p.getUsername())){

                throw  new ApiRequestException("003");
            }

            if (userService.existsByEmail(p.getEmail())){

                throw  new ApiRequestException("004");
            }
        }

        if(!Util.validEmail(p.getEmail())){

            throw  new ApiRequestException("006");
        }

        try{

            String encodedPassword = bCryptPasswordEncoder.encode(p.getPassword());
            p.setPassword(encodedPassword);
            User temporal = userService.create(p);

            return responseHandler.generateResponse("000",temporal);
        }
        catch(Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @GetMapping
    private ResponseEntity<Object> getAllUsers(){

        try{

            List<User> users = userService.getAllUsers();
            return responseHandler.generateResponse("000",users);
        }
        catch(Exception e){

            throw new ApiRequestException("002");
        }
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Object> deleteById(@RequestParam("id") Long id) {

        Optional<User> user= userService.findById(id);
        List<Role> roles = user.get().getRolesUser();

        if(!user.isPresent()){

            throw new ApiRequestException("005");
        }

        try {

            userService.deleteById(id);
            user.get().setRolesUser(null);
            return responseHandler.generateResponse("000",user);
        }
        catch (Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @RequestMapping(params = "id")
    public ResponseEntity<Object> getByName(@RequestParam("id") Long id) {

        try {

            Optional<User> user = userService.findById(id);
            return responseHandler.generateResponse("000",user);
        }
        catch (Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @RequestMapping(params = "username")
    public ResponseEntity<Object> getByLastname(@RequestParam("username") String username) {
        //return ResponseEntity.ok(userService.findByUsername(username));
        try {

            Optional<User> user = userService.findByUsername(username);
            return responseHandler.generateResponse("000",user);
        }
        catch (Exception e){

            throw  new ApiRequestException("002");
        }
    }
}
