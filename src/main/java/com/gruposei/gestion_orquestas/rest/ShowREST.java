package com.gruposei.gestion_orquestas.rest;

import com.gruposei.gestion_orquestas.model.Role;
import com.gruposei.gestion_orquestas.model.Show;
import com.gruposei.gestion_orquestas.responses.ApiRequestException;
import com.gruposei.gestion_orquestas.responses.ResponseHandler;
import com.gruposei.gestion_orquestas.service.RoleService;
import com.gruposei.gestion_orquestas.service.ShowService;
import com.gruposei.gestion_orquestas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shows")
public class ShowREST {

    @Autowired
    private ShowService showService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ResponseHandler responseHandler;

    @PostMapping
    private ResponseEntity<Object> save(@RequestBody Show p){

        try{

            Show temporal = showService.create(p);
            return responseHandler.generateResponse("000",temporal);
        }
        catch(Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @GetMapping
    private ResponseEntity<Object> getAll(){

        try{

            List<Show> cloths = showService.getAll();
            return responseHandler.generateResponse("000",cloths);
        }
        catch(Exception e){

            throw new ApiRequestException("002");
        }
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Object> deleteById(@RequestParam("id") Long id) {

        Optional<Show> cloth= showService.findById(id);

        if(!cloth.isPresent()){

            throw new ApiRequestException("005");
        }

        try {
            showService.deleteById(id);
            return responseHandler.generateResponse("000",cloth);
        }
        catch (Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @RequestMapping(params = "id")
    public ResponseEntity<Object> getById(@RequestParam("id") Long id) {

        Optional<Show> cloth= showService.findById(id);

        if(!cloth.isPresent()){

            throw new ApiRequestException("005");
        }
        try {

            return responseHandler.generateResponse("000",cloth);
        }
        catch (Exception e){

            throw  new ApiRequestException("002");
        }
    }
}
