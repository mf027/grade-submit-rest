package com.mircea.gradesubmitrest;

import java.util.Arrays;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mircea.gradesubmitrest.exception.EntityNotFoundException;
import com.mircea.gradesubmitrest.exception.ErrorResponse;
import com.mircea.gradesubmitrest.exception.GradeNotFoundException;
import com.mircea.gradesubmitrest.exception.StudentNotEnrolledException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, GradeNotFoundException.class, StudentNotEnrolledException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList("Data integrity violation, we cannot process your request"));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
}
