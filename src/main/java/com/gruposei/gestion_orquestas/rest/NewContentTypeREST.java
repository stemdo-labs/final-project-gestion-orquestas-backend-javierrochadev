package com.gruposei.gestion_orquestas.rest;

import com.gruposei.gestion_orquestas.model.MusicalResourceType;
import com.gruposei.gestion_orquestas.model.NewContentType;
import com.gruposei.gestion_orquestas.responses.ApiRequestException;
import com.gruposei.gestion_orquestas.responses.ResponseHandler;
import com.gruposei.gestion_orquestas.service.NewContentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nc_types")
public class NewContentTypeREST {

    @Autowired
    private NewContentTypeService newContentTypeService;

    @Autowired
    private ResponseHandler responseHandler;

    @PostMapping
    private ResponseEntity<Object> save(@RequestBody NewContentType p){

        try{

            NewContentType temporal = newContentTypeService.create(p);
            return responseHandler.generateResponse("000",temporal);
        }
        catch(Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @GetMapping
    private ResponseEntity<Object> getAll(){

        try{

            List<NewContentType> cloths = newContentTypeService.getAll();
            return responseHandler.generateResponse("000",cloths);
        }
        catch(Exception e){

            throw new ApiRequestException("002");
        }
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Object> deleteById(@RequestParam("id") Long id) {

        Optional<NewContentType> cloth= newContentTypeService.findById(id);

        if(!cloth.isPresent()){

            throw new ApiRequestException("005");
        }

        try {
            newContentTypeService.deleteById(id);
            return responseHandler.generateResponse("000",cloth);
        }
        catch (Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @RequestMapping(params = "id")
    public ResponseEntity<Object> getById(@RequestParam("id") Long id) {

        Optional<NewContentType> cloth= newContentTypeService.findById(id);

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

        Optional<NewContentType> cloth= newContentTypeService.findBySystemName(systemName);

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
