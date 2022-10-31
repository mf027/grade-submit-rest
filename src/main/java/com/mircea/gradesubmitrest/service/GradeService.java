package com.mircea.gradesubmitrest.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.mircea.gradesubmitrest.entity.Grade;

public interface GradeService {

    Grade addGrade(Grade grade, Long studentId, Long courseId);
    Grade updateGrade(Double points, Long studentId, Long courseId);
    void deleteGrade(Long studentId, Long courseId);
    Grade getGrade(Long studentId, Long courseId);
    List<Grade> getGrades();
    List<Grade> getCourseGrades(Long courseId);
    List<Grade> getStudentGrades(Long studentId);
    void exportToExcel(HttpServletResponse response) throws IOException;
    
}
