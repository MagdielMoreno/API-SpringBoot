package com.magdiel.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.magdiel.api.models.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{
    void deleteStudentById(Long id);
    Student findStudentById(Long id);
}
