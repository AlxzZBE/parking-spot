package com.api.parkingcontrol.handler;

import com.api.parkingcontrol.exceptions.AlreadyExistsException;
import com.api.parkingcontrol.exceptions.ExceptionDetails;
import com.api.parkingcontrol.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(NotFoundException nfe) {
        return new ResponseEntity<>(new ExceptionDetails(
                nfe, "Not Found Exception, Check the Details and the Documentation"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionDetails> handlerAlreadyExistsException(AlreadyExistsException aee) {
        return new ResponseEntity<>(new ExceptionDetails(
                aee, "Already Exists Exception, Check the Details and the Documentation"), HttpStatus.CONFLICT);
    }
}