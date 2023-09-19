package com.picpayservice.configuration.exceptions;

public class NotEnoughBalanceException  extends RuntimeException{
    public NotEnoughBalanceException(String message){
        super(message);
    }
}
