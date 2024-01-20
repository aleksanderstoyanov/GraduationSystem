package com.graduation.system.data.repository;

import com.graduation.system.data.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findByName(String name);
}
