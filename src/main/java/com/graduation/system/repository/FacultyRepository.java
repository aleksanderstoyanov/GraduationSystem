package com.graduation.system.repository;

import com.graduation.system.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findByName(String name);
}
