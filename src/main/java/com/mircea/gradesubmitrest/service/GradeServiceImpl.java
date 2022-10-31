package com.mircea.gradesubmitrest.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mircea.gradesubmitrest.entity.Course;
import com.mircea.gradesubmitrest.entity.Grade;
import com.mircea.gradesubmitrest.entity.Student;
import com.mircea.gradesubmitrest.exception.GradeNotFoundException;
import com.mircea.gradesubmitrest.exception.StudentNotEnrolledException;
import com.mircea.gradesubmitrest.output.ExcelGenerator;
import com.mircea.gradesubmitrest.repository.GradeRepository;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @Override
    public Grade addGrade(Grade grade, Long studentId, Long courseId) {
        Student student = studentService.getStudent(studentId);
        Course course = courseService.getCourse(courseId);
        if (!(course.getStudents().contains(student))) throw new StudentNotEnrolledException(studentId, courseId);
        grade.setStudent(student);
        grade.setCourse(course);
        return gradeRepository.save(grade);
    }

    @Override
    public Grade updateGrade(Double points, Long studentId, Long courseId) {
       Grade foundGrade = unwrapGrade(gradeRepository.findByStudentIdAndCourseId(studentId, courseId), studentId, courseId);
       foundGrade.setPoints(points);
       return gradeRepository.save(foundGrade);
    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {
        gradeRepository.deleteByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        return unwrapGrade(gradeRepository.findByStudentIdAndCourseId(studentId, courseId), studentId, courseId);
    }

    @Override
    public List<Grade> getGrades() {
        return (List<Grade>)gradeRepository.findAll();
    }


    public static Grade unwrapGrade(Optional<Grade> entity, Long studentId, Long courseId) {
        if (entity.isPresent()) return entity.get();
        else throw new GradeNotFoundException(studentId, courseId);
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Student_Grades" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        ExcelGenerator generator = new ExcelGenerator(getGrades());
        generator.generateExcelFile(response);

    }
    
}
