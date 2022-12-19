package com.mircea.gradesubmitrest.exception;


public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " with the id " + id + " does not exist in our database");
    }
    
}
