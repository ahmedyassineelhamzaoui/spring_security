package com.example.security.common.exceptions.custom;

public class InvalidOauth2Exception extends  RuntimeException{

    public InvalidOauth2Exception(String message ){
        super(message);
    }
}
