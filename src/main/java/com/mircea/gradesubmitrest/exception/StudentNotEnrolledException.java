package com.mircea.gradesubmitrest.exception;

public class StudentNotEnrolledException extends RuntimeException {

    public StudentNotEnrolledException(Long studentId, Long courseId) {
        super("The student with the id " + studentId + " is not enrolled for the course with the id " + courseId); 
    }

}
