package com.meli.socialmeli.exceptions;

import com.meli.socialmeli.dtos.MessageDto;
import com.meli.socialmeli.exceptions.custom.BadRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> badRequestException(Exception e){
        System.err.println(Arrays.toString(e.getStackTrace()));
        return ResponseEntity.badRequest().body(new MessageDto(e.getMessage()));
    }

}
