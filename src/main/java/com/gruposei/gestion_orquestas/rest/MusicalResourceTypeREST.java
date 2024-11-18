package com.gruposei.gestion_orquestas.rest;

import com.gruposei.gestion_orquestas.model.MusicalResourceType;
import com.gruposei.gestion_orquestas.responses.ApiRequestException;
import com.gruposei.gestion_orquestas.responses.ResponseHandler;
import com.gruposei.gestion_orquestas.service.MusicalResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mr_types")
public class MusicalResourceTypeREST {

    @Autowired
    private MusicalResourceTypeService musicalResourceTypeService;

    @Autowired
    private ResponseHandler responseHandler;

    @PostMapping
    private ResponseEntity<Object> save(@RequestBody MusicalResourceType p){

        try{

            MusicalResourceType temporal = musicalResourceTypeService.create(p);
            return responseHandler.generateResponse("000",temporal);
        }
        catch(Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @GetMapping
    private ResponseEntity<Object> getAll(){

        try{

            List<MusicalResourceType> cloths = musicalResourceTypeService.getAll();
            return responseHandler.generateResponse("000",cloths);
        }
        catch(Exception e){

            throw new ApiRequestException("002");
        }
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Object> deleteById(@RequestParam("id") Long id) {

        Optional<MusicalResourceType> cloth= musicalResourceTypeService.findById(id);

        if(!cloth.isPresent()){

            throw new ApiRequestException("005");
        }

        try {
            musicalResourceTypeService.deleteById(id);
            return responseHandler.generateResponse("000",cloth);
        }
        catch (Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @RequestMapping(params = "id")
    public ResponseEntity<Object> getById(@RequestParam("id") Long id) {

        Optional<MusicalResourceType> cloth= musicalResourceTypeService.findById(id);

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

    @RequestMapping(params = "systemName")
    public ResponseEntity<Object> getById(@RequestParam("systemName") String systemName) {

        Optional<MusicalResourceType> cloth = musicalResourceTypeService.findBySystemName(systemName);

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
