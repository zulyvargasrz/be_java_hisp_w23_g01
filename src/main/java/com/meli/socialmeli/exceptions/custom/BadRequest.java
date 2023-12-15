package com.meli.socialmeli.exceptions.custom;

public class BadRequest extends RuntimeException{
    public BadRequest(String message) {
        super(message);
    }
}
