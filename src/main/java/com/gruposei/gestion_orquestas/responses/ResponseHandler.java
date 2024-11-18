package com.gruposei.gestion_orquestas.responses;

import com.gruposei.gestion_orquestas.model.MyError;
import com.gruposei.gestion_orquestas.service.MyErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseHandler {

    @Autowired
    private  MyErrorService myErrorService;

    public  ResponseEntity<Object> generateResponse(String error, Object responseObj) {

        MyError myError = myErrorService.findById(error).get();

        Map<String, Object> map = new LinkedHashMap<String, Object>();

            map.put("message", myError.getMessage());
            map.put("httpStatus", myError.getHttpStatus());
            map.put("code", myError.getHttpStatus().value());
            map.put("zonedDateTime", ZonedDateTime.now(ZoneId.of("Z")));
            map.put("data", responseObj);

            return new ResponseEntity<Object>(map,myError.getHttpStatus());
    }

    public  ResponseEntity<Object> generateResponse(String error) {

        MyError myError = myErrorService.findById(error).get();

        Map<String, Object> map = new LinkedHashMap<String, Object>();

        map.put("message", myError.getMessage());
        map.put("httpStatus", myError.getHttpStatus());
        map.put("code", myError.getHttpStatus().value());
        map.put("zonedDateTime", ZonedDateTime.now(ZoneId.of("Z")));
        map.put("data", null);

        return new ResponseEntity<Object>(map,myError.getHttpStatus());
    }
}