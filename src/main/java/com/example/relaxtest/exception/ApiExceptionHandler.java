package com.example.relaxtest.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;


@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {NotUploadedFileException.class})
    public ResponseEntity<Object> handleNotUploadedFileException(NotUploadedFileException e){
        ApiErrorResponse apiException=new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,e.getMessage()
        );
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {FileNotFoundException.class})
    public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException e){
        ApiErrorResponse apiException=new ApiErrorResponse(
               HttpStatus.BAD_REQUEST,e.getMessage()
        );
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {SequenceNotFoundException.class})
    public ResponseEntity<Object> handleSequenceNotFoundException(SequenceNotFoundException e){
        ApiErrorResponse apiException=new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,e.getMessage()
        );
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
}