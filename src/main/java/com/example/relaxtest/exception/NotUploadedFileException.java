package com.example.relaxtest.exception;

public class NotUploadedFileException extends RuntimeException{
    public NotUploadedFileException(String message){
        super(message);
    }
}