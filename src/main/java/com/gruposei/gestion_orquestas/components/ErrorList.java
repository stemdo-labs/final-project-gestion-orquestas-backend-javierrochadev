package com.gruposei.gestion_orquestas.components;

import com.gruposei.gestion_orquestas.model.MyError;
import com.gruposei.gestion_orquestas.service.MyErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Order(6)
public class ErrorList implements CommandLineRunner {

    @Autowired
    private MyErrorService myErrorService;

    @Override
    public void run(String... args) throws Exception {

        MyError e = new MyError();

        e.setId("000");
        e.setMessage("OK");
        e.setHttpStatus(HttpStatus.OK);
        e.setEndpoint("GENERAL");
        myErrorService.create(e);

        e.setId("001");
        e.setMessage("Usuario o contraseña incorrectos");
        e.setHttpStatus(HttpStatus.FORBIDDEN);
        e.setEndpoint("LOGIN");
        myErrorService.create(e);

        e.setId("002");
        e.setMessage("Ocurrió un error inesperado. Consulte con el administrador del sistema");
        e.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        e.setEndpoint("GENERAL");
        myErrorService.create(e);

        e.setId("003");
        e.setMessage("Nombre de usuario en uso Intente con uno distinto");
        e.setHttpStatus(HttpStatus.BAD_REQUEST);
        e.setEndpoint("USER");
        myErrorService.create(e);

        e.setId("004");
        e.setMessage("Mail en uso. Intente con uno distinto");
        e.setHttpStatus(HttpStatus.BAD_REQUEST);
        e.setEndpoint("USER");
        myErrorService.create(e);

        e.setId("005");
        e.setMessage("No existe el valor ingresado");
        e.setHttpStatus(HttpStatus.BAD_REQUEST);
        e.setEndpoint("GENERAL");
        myErrorService.create(e);

        e.setId("006");
        e.setMessage("El email ingresado tiene un formato incorrecto");
        e.setHttpStatus(HttpStatus.BAD_REQUEST);
        e.setEndpoint("USER");
        myErrorService.create(e);

        e.setId("007");
        e.setMessage("El usuario ingresado no existe");
        e.setHttpStatus(HttpStatus.BAD_REQUEST);
        e.setEndpoint("USER");
        myErrorService.create(e);

        e.setId("008");
        e.setMessage("El show ingresado no existe");
        e.setHttpStatus(HttpStatus.BAD_REQUEST);
        e.setEndpoint("SHOW");
        myErrorService.create(e);

        e.setId("009");
        e.setMessage("No se encontró requerimiento de pago con ese External Reference");
        e.setHttpStatus(HttpStatus.BAD_REQUEST);
        e.setEndpoint("TICKET");
        myErrorService.create(e);

        e.setId("010");
        e.setMessage("imagen no válida");
        e.setHttpStatus(HttpStatus.BAD_REQUEST);
        e.setEndpoint("IMAGE");
        myErrorService.create(e);

        e.setId("011");
        e.setMessage("La imagen no existe");
        e.setHttpStatus(HttpStatus.BAD_REQUEST);
        e.setEndpoint("IMAGE");
        myErrorService.create(e);

        e.setId("012");
        e.setMessage("El pago todavía no fue efectuado");
        e.setHttpStatus(HttpStatus.BAD_REQUEST);
        e.setEndpoint("TICKET");
        myErrorService.create(e);

        e.setId("013");
        e.setMessage("Ticket ya usado o inexistente");
        e.setHttpStatus(HttpStatus.FORBIDDEN);
        e.setEndpoint("TICKET");
        myErrorService.create(e);
    }
}
