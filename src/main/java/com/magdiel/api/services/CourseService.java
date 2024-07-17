package com.magdiel.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.magdiel.api.models.Course;
import com.magdiel.api.repositories.CourseRepo;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    public Course add(Course course){ 
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }

        if (course.getId() != null || course.getName() == null || course.getDescription() == null) {
            String error = "Incorrect Request, Should be:\n" +
                           "{\n" +
                           "  \"name\": \"example\",\n" +
                           "  \"description\": \"short description\",\n" +
                           "}";
            throw new IllegalArgumentException(error);
        }

        return courseRepo.save(course); 
    }

    public Course update(Course course){
        if (!courseRepo.existsById(course.getId())) {
            throw new IllegalArgumentException("Course to update not found");
        }

        if (course.getName() == null || course.getDescription() == null) {
            String error = "Incorrect Request, Should be:\n" +
                           "{\n" +
                           "  \"id\": 0,\n" +
                           "  \"name\": \"example\",\n" +
                           "  \"description\": \"short description\",\n" +
                           "}";
            throw new IllegalArgumentException(error);
        }

        return courseRepo.save(course);
    }

    public void delete(Long id){ 
        if (!courseRepo.existsById(id)) {
            throw new IllegalArgumentException("Course not found with id: " + id);
        }
        courseRepo.deleteById(id); 
    }

    public Course find(Long id){ 
        Course course = courseRepo.findCourseById(id);
        if (course == null) {
            throw new IllegalArgumentException("Course not found with id: " + id);
        }
        return course;
    }

    public List<Course> findAll(){
        return courseRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}
