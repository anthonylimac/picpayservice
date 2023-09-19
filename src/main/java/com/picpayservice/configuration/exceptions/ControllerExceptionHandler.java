package com.picpayservice.configuration.exceptions;

import com.picpayservice.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("User already exist", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threatNotFoundException(EntityNotFoundException exception){
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(MerchantException.class)
    public ResponseEntity threatMerchantException(MerchantException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "400");
        return  ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    public ResponseEntity threatLowBalanceException(NotEnoughBalanceException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "400");
        return  ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity threatUserNotFoundException(UserNotFoundException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
        return  ResponseEntity.badRequest().body(exceptionDTO);
    }
}
