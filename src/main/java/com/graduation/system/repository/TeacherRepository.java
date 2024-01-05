package com.graduation.system.repository;

import com.graduation.system.entity.Student;
import com.graduation.system.entity.Teacher;
import com.graduation.system.enums.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByEgn(String egn);
}
