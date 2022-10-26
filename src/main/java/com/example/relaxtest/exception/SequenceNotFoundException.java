package com.example.relaxtest.exception;

import org.springframework.http.HttpStatus;

public class SequenceNotFoundException extends RuntimeException{
    public SequenceNotFoundException(String message){
        super(message);
    }
}