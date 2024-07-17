package com.magdiel.api.controllers;

import java.util.List;
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
import com.magdiel.api.models.Course;
import com.magdiel.api.services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAll(){
        List<Course> courses = courseService.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Course course = courseService.find(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> add(@RequestBody Course course){
        Course newCourse = courseService.add(course);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Course> update(@RequestBody Course course){
        Course updatedCourse = courseService.update(course);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMultiple(@RequestBody List<Long> ids){
        for (Long id : ids) {
            courseService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
