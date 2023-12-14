package com.example.demo.controllers.exceptions;


public class PostNotFoundException extends Throwable{
    public PostNotFoundException(long postId){
        super("Post with id " + postId + " not found");
    }
}
