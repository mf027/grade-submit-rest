package com.mircea.gradesubmitrest.exception;

public class GradeNotFoundException extends RuntimeException {

    public GradeNotFoundException(Long studentId, Long courseId) {
        super("The grade with student id " + studentId + " for the course with id " + courseId + " does not exits in our database!");
    }
    
}
