package com.example.security.common.exceptions.custom;

public class EmailVerificationException  extends  RuntimeException{
    public EmailVerificationException(String message){
        super(message);
    }
}
