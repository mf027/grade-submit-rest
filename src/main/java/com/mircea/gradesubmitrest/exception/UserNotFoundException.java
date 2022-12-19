package com.mircea.gradesubmitrest.exception;


public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException (Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " does not exist in our records");
    }
    
}
