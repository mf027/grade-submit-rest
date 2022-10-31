package com.mircea.gradesubmitrest.service;

import java.util.List;

import com.mircea.gradesubmitrest.entity.Student;

public interface StudentService {

    Student addStudent(Student student);
    Student updateStudent(Student student, Long studentId);
    void deleteStudent(Long id);
    Student getStudent(Long id);
    List<Student> getStudents();
    
}
