package com.gruposei.gestion_orquestas.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;
import com.gruposei.gestion_orquestas.model.MyError;
import com.gruposei.gestion_orquestas.model.Role;
import com.gruposei.gestion_orquestas.model.Ticket;
import com.gruposei.gestion_orquestas.model.User;
import com.gruposei.gestion_orquestas.service.MyErrorService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Optional;

public class testingThings {



    public static void main(String[] args) throws IOException, WriterException, DocumentException, MessagingException {

        boolean result = isNull(1);
        System.out.println(result);
 }
    public static boolean isNull (Object obj) {

            if(obj == null) {

                return true;
            }
            else{
                return false;
            }
        }

}
