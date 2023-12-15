package com.meli.socialmeli.exceptions;

import com.meli.socialmeli.dtos.response.ExceptionDTO;
import com.meli.socialmeli.exceptions.custom.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handlerNotFoundExcepcion(NotFoundException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}