package com.gruposei.gestion_orquestas.rest;

import com.gruposei.gestion_orquestas.model.Show;
import com.gruposei.gestion_orquestas.model.Song;
import com.gruposei.gestion_orquestas.responses.ApiRequestException;
import com.gruposei.gestion_orquestas.responses.ResponseHandler;
import com.gruposei.gestion_orquestas.service.ShowService;
import com.gruposei.gestion_orquestas.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
public class SongREST {

    @Autowired
    private SongService songService;

    @Autowired
    private ResponseHandler responseHandler;

    @PostMapping
    private ResponseEntity<Object> save(@RequestBody Song p){

        try{

            Song temporal = songService.create(p);
            return responseHandler.generateResponse("000",temporal);
        }
        catch(Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @GetMapping
    private ResponseEntity<Object> getAll(){

        try{

            List<Song> cloths = songService.getAll();
            return responseHandler.generateResponse("000",cloths);
        }
        catch(Exception e){

            throw new ApiRequestException("002");
        }
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Object> deleteById(@RequestParam("id") Long id) {

        Optional<Song> cloth= songService.findById(id);

        if(!cloth.isPresent()){

            throw new ApiRequestException("005");
        }

        try {
            songService.deleteById(id);
            return responseHandler.generateResponse("000",cloth);
        }
        catch (Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @RequestMapping(params = "id")
    public ResponseEntity<Object> getById(@RequestParam("id") Long id) {

        Optional<Song> cloth= songService.findById(id);

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
