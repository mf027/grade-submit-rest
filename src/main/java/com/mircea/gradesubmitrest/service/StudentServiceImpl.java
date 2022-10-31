package com.mircea.gradesubmitrest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mircea.gradesubmitrest.entity.Student;
import com.mircea.gradesubmitrest.exception.EntityNotFoundException;
import com.mircea.gradesubmitrest.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student, Long studentId) {
        Student foundStudent = unwrapStudent(studentRepository.findById(studentId), studentId);
        return addStudent(updateFields(foundStudent, student));
    }

    @Override
    public Student getStudent(Long id) {
        return unwrapStudent(studentRepository.findById(id), id);
    }

    @Override
    public List<Student> getStudents() {
        return (List<Student>)studentRepository.findAll();
    }

    @Override
    public void deleteStudent(Long id) {
        unwrapStudent(studentRepository.findById(id), id);
        studentRepository.deleteById(id);        
    }

    public static Student unwrapStudent(Optional<Student> entity, Long studentId) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(studentId, Student.class);
    }
    
    private Student updateFields(Student oldStudent, Student newStudent) {
        if (newStudent.getName() == null || !(newStudent.getName().equals(oldStudent.getName()))) oldStudent.setName(newStudent.getName());
        if (newStudent.getYear() != oldStudent.getYear()) newStudent.setYear(oldStudent.getYear());
        
        return oldStudent;
    }
}
