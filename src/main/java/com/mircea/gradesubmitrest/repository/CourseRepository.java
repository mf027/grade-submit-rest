package com.mircea.gradesubmitrest.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mircea.gradesubmitrest.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

   Optional<Course> findByCode(String code);
    
}
