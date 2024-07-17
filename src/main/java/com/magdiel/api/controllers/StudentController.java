package com.magdiel.api.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.magdiel.api.models.Student;
import com.magdiel.api.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAll(){
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Student student = studentService.find(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student){
        Student newStudent = studentService.add(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student){
        Student updatedStudent = studentService.update(student);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMultiple(@RequestBody List<Long> ids){
        for (Long id : ids) {
            studentService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addCourse")
    public ResponseEntity<?> addCourse(@RequestBody Map<String, Long> request) {
        studentService.addCourse(request.get("studentId"), request.get("courseId"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/removeCourse")
    public ResponseEntity<?> removeCourse(@RequestBody Map<String, Long> request) {
        studentService.removeCourse(request.get("studentId"), request.get("courseId"));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
