package com.gruposei.gestion_orquestas.responses;

import com.gruposei.gestion_orquestas.model.MyError;
import com.gruposei.gestion_orquestas.service.MyErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@ControllerAdvice
public class ApiExceptionHandler {

    @Autowired
    private MyErrorService myErrorService;

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){

        MyError error = myErrorService.findById(e.getMessage()).get();

        HttpStatus badRequest = error.getHttpStatus();
        String message = error.getMessage();
        ApiException apiException = new ApiException(
                message,
                badRequest,
                badRequest.value(),
                ZonedDateTime.now(ZoneId.of("Z")),
                null);

        return new ResponseEntity<>(apiException, badRequest);
    }
}
