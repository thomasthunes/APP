package com.example.demo.controllers.exceptions;

public class UserNotFoundException extends Throwable{

    public UserNotFoundException(long customerId){
        super("User with id " + customerId + " was not found.");
    }

    public UserNotFoundException(String email){
        super("User with id " + email + " was not found.");
    }
}
