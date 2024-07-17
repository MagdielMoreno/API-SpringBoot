package com.magdiel.api.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.magdiel.api.models.Course;
import com.magdiel.api.models.Student;
import com.magdiel.api.repositories.CourseRepo;
import com.magdiel.api.repositories.StudentRepo;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    public Student add(Student student){ 
        if (student.getId() != null || student.getName() == null || student.getEmail() == null || student.getPhone() == null) {
            String error = "Incorrect Request, Should be:\n" +
                           "{\n" +
                           "  \"name\": \"example\",\n" +
                           "  \"email\": \"example@gmail.com\",\n" +
                           "  \"phone\": \"6671234567\"\n" +
                           "}";
            throw new IllegalArgumentException(error);
        }

        return studentRepo.save(student); 
    }

    public Student update(Student student){
        if (student.getName() == null || student.getEmail() == null || student.getPhone() == null) {
            String error = "Incorrect Request, Should be:\n" +
                           "{\n" +
                           "  \"id\": 0,\n" +
                           "  \"name\": \"example\",\n" +
                           "  \"email\": \"example@gmail.com\",\n" +
                           "  \"phone\": \"6671234567\"\n" +
                           "}";
            throw new IllegalArgumentException(error);
        }

        if (!studentRepo.existsById(student.getId())) {
            throw new IllegalArgumentException("Student to update not found");
        }

        return studentRepo.save(student);
    }

    public void delete(Long id){ 
        if (!studentRepo.existsById(id)) {
            throw new IllegalArgumentException("Student not found with id: " + id);
        }
        studentRepo.deleteById(id); 
    }

    public Student find(Long id){ 
        Student student = studentRepo.findStudentById(id);
        if (student == null) {
            throw new IllegalArgumentException("Student not found with id: " + id);
        }
        return student;
    }

    @Transactional
    public List<Student> findAll(){
        return studentRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
    
    public void addCourse(Long studentId, Long courseId) {
        if (studentId == null || courseId == null) {
            String error = "Incorrect Request, Should be:\n" +
                           "{\n" +
                           "  \"studentId\": 0,\n" +
                           "  \"courseId\": \"0\",\n" +
                           "}";
            throw new IllegalArgumentException(error);
        }

        if (!studentRepo.existsById(studentId)){
            throw new IllegalArgumentException("Student not found");
        }

        if (!courseRepo.existsById(courseId)){
            throw new IllegalArgumentException("Course not found");
        }

        Student student = studentRepo.findStudentById(studentId);
        Course course = courseRepo.findCourseById(courseId);

        if (student != null && course != null) {
            if (student.getCourses().contains(course)) {
                throw new IllegalArgumentException("The course is already enrolled by the student");
            }
            student.addCourse(course);
            studentRepo.save(student);
        }
    }

    public void removeCourse(Long studentId, Long courseId) {
        if (studentId == null || courseId == null) {
            String error = "Incorrect Request, Should be:\n" +
                           "{\n" +
                           "  \"studentId\": 0,\n" +
                           "  \"courseId\": \"0\",\n" +
                           "}";
            throw new IllegalArgumentException(error);
        }

        if (!studentRepo.existsById(studentId)){
            throw new IllegalArgumentException("Student not found");
        }

        if (!courseRepo.existsById(courseId)){
            throw new IllegalArgumentException("Course not found");
        }

        Student student = studentRepo.findStudentById(studentId);
        Course course = courseRepo.findCourseById(courseId);

        if (student != null && course != null) {
            if (!student.getCourses().contains(course)) {
                throw new IllegalArgumentException("The course is not enrolled by the student");
            }
            student.removeCourse(course);
            studentRepo.save(student);
        }
    }
}
