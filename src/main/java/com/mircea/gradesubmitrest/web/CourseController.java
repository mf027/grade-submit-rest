package com.mircea.gradesubmitrest.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mircea.gradesubmitrest.entity.Course;
import com.mircea.gradesubmitrest.entity.Student;
import com.mircea.gradesubmitrest.service.CourseService;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
    }

    @PostMapping("/submit")
    public ResponseEntity<Course> submitCourse(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.addCourse(course), HttpStatus.CREATED);
    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable Long courseId) {
        return new ResponseEntity<Course>(courseService.updateCourse(course, courseId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/enroll/course/{courseId}/student/{studentId}")
    public ResponseEntity<Course> enrollStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        return new ResponseEntity<>(courseService.enrollStudent(courseId, studentId), HttpStatus.OK);
    }

    @PutMapping("/unenroll/course/{courseId}/student/{studentId}")
    public ResponseEntity<Course> unEnrollStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        return new ResponseEntity<Course>(courseService.unEnrollStudent(courseId, studentId), HttpStatus.OK);
    }

    @GetMapping("/students/{courseId}")
    public ResponseEntity<Set<Student>> getEnrolledStudents(@PathVariable Long courseId) {
        return new ResponseEntity<>(courseService.getEnrolledStudents(courseId), HttpStatus.OK);
    }
    
    
}
