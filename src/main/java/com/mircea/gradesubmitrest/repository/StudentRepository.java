package com.mircea.gradesubmitrest.repository;

import org.springframework.data.repository.CrudRepository;

import com.mircea.gradesubmitrest.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
    
    
}
