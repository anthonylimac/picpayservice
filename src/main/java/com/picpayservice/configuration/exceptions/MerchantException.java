package com.picpayservice.configuration.exceptions;

public class MerchantException extends RuntimeException{
    public MerchantException(String message, Exception e){
        super(message, e);
    }

    public MerchantException(String message){
        super(message);
    }
}
