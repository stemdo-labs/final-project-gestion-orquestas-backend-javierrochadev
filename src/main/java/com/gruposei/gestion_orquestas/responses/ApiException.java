package com.gruposei.gestion_orquestas.responses;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {

    private final String message;
    private final HttpStatus httpStatus;
    private final int code;
    private final ZonedDateTime zonedDateTime;
    private final Object data;

    public ApiException(String message, HttpStatus httpStatus, int code, ZonedDateTime zonedDateTime, Object data) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.code = code;
        this.zonedDateTime = zonedDateTime;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getCode() {
        return code;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public Object getData() {
        return data;
    }
}
