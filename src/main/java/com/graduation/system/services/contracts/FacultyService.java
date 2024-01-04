package com.graduation.system.services.contracts;

import com.graduation.system.entity.Faculty;

public interface FacultyService {
    Faculty getByName(String facultyName);
    void create(String name);
}
