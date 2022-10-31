package com.mircea.gradesubmitrest.service;

import java.util.List;
import java.util.Set;

import com.mircea.gradesubmitrest.entity.Course;
import com.mircea.gradesubmitrest.entity.Student;


public interface CourseService {

    Course addCourse(Course course);
    Course updateCourse(Course course, Long courseId);
    void deleteCourse(Long id);
    List<Course> getCourses();
    Course getCourse(Long id);
    Course enrollStudent(Long courseId, Long studentId);
    Set<Student> getEnrolledStudents(Long courseId);
    Course unEnrollStudent(Long courseId, Long studentId);
    
}
