package com.graduation.system.repository;

import com.graduation.system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "select * FROM student where deleted = 0",
            nativeQuery = true)
    List<Student> getAll();
    Student findByEgn(String egn);
}
