package com.meli.socialmeli.exception.custom;

public class BadRequest extends RuntimeException{
    public BadRequest(String message) {
        super(message);
    }
}
