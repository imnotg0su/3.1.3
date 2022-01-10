package com.gubaevpavel.spring.boot311.exception_handing;

public class NoSuchUserException extends RuntimeException{

    public NoSuchUserException(String message) {
        super(message);
    }
}
