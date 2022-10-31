package com.mircea.gradesubmitrest.web;



import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mircea.gradesubmitrest.entity.Grade;
import com.mircea.gradesubmitrest.service.GradeService;

@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    GradeService gradeService;



    @GetMapping("student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> getGrade(@PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<Grade>(gradeService.getGrade(studentId, courseId), HttpStatus.OK);
    }

    @PostMapping("/submit/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> submitGrade(@RequestBody Grade grade, @PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<>(gradeService.addGrade(grade, studentId, courseId), HttpStatus.CREATED);
    }

    @PutMapping("update/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> updateGrade(@RequestBody Grade grade, @PathVariable Long studentId,@PathVariable Long courseId) {
        return new ResponseEntity<Grade>(gradeService.updateGrade(grade.getPoints(), studentId, courseId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("delete/student/{studentId}/course/{courseId}")
    public ResponseEntity<HttpStatus> deleteGrade(@PathVariable Long studentId,@PathVariable Long courseId) {
        gradeService.deleteGrade(studentId, courseId);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/course/{courseId}/all")
    public ResponseEntity<List<Grade>> getCourseGrades(@PathVariable Long courseId) {
        return new ResponseEntity<>(gradeService.getCourseGrades(courseId), HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/all")
    public ResponseEntity<List<Grade>> getStudentGrades(@PathVariable Long studentId) {
        return new ResponseEntity<>(gradeService.getStudentGrades(studentId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Grade>> getAllGrades() {
        return new ResponseEntity<>(gradeService.getGrades(), HttpStatus.OK);
    }

    @GetMapping("/saveFile")
    public ResponseEntity<HttpStatus> downloadExcelFile(HttpServletResponse response) {
        try{
            gradeService.exportToExcel(response);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
