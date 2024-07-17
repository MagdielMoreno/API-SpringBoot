package com.magdiel.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.magdiel.api.models.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long>{
    void deleteCourseById(Long id);
    Course findCourseById(Long id);
}
