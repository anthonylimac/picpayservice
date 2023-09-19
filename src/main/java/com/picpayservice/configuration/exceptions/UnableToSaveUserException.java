package com.picpayservice.configuration.exceptions;

public class UnableToSaveUserException extends RuntimeException{
    public UnableToSaveUserException(String message, Exception e){
        super(message, e);
    }

    public UnableToSaveUserException(String message){
        super(message);
    }
}
