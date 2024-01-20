package com.graduation.system.services.contracts;

import com.graduation.system.data.entity.Faculty;

public interface FacultyService {
    Faculty getByName(String facultyName);
    void create(String name);
}
