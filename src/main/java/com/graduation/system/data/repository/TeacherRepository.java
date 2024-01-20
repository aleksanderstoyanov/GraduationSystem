package com.graduation.system.data.repository;

import com.graduation.system.data.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByEgn(String egn);
}
