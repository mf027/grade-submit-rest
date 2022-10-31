package com.mircea.gradesubmitrest.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mircea.gradesubmitrest.entity.Course;
import com.mircea.gradesubmitrest.entity.Student;
import com.mircea.gradesubmitrest.repository.CourseRepository;
import com.mircea.gradesubmitrest.exception.EntityNotFoundException;
import com.mircea.gradesubmitrest.exception.StudentNotEnrolledException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    
    CourseRepository courseRepository;
    StudentService studentService;

    @Override
    public Course addCourse(Course course) {
            return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course, Long courseId) {
        Course foundCourse = unwrapCourse(courseRepository.findById(courseId), courseId);
        return addCourse(updateFields(foundCourse, course));
    }

    @Override
    public void deleteCourse(Long id) {
        unwrapCourse(courseRepository.findById(id), id);
        courseRepository.deleteById(id);        
    }

    @Override
    public List<Course> getCourses() {
      return (List<Course>)courseRepository.findAll();
    }

    @Override
    public Course getCourse(Long id) {
        return unwrapCourse(courseRepository.findById(id), id);
    }

    public static Course unwrapCourse(Optional<Course> entity, Long courseId) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(courseId, Course.class);
    }
    
    private Course updateFields(Course oldCourse, Course newCourse) {
        if (newCourse.getCode() != null || !(oldCourse.getCode().equals(newCourse.getCode()))) oldCourse.setCode(newCourse.getCode());
        if (newCourse.getName() != null) oldCourse.setName(newCourse.getName());
        if (newCourse.getDescription() != null) oldCourse.setDescription(newCourse.getDescription());

        return oldCourse;
    }

    @Override
    public Course enrollStudent(Long courseId, Long studentId) {
        Course course = getCourse(courseId);
        Student student = studentService.getStudent(studentId);
        course.getStudents().add(student);
        return addCourse(course);
    }

    public Set<Student> getEnrolledStudents(Long courseId) {
        Course course = getCourse(courseId);
        return course.getStudents();
    }

    @Override
    public Course unEnrollStudent(Long courseId, Long studentId) {
        Course course = getCourse(courseId);
        Student student = studentService.getStudent(studentId);
        if (!(course.getStudents().contains(student))) throw new StudentNotEnrolledException(studentId, courseId);
        course.getStudents().remove(student);
        return addCourse(course);
    }

}
