package com.graduation.system.services.contracts;

import com.graduation.system.entity.Teacher;
import com.graduation.system.enums.Position;

public interface TeacherService {
    Position getPositionByName(String name);
    void createTeacher(Teacher teacher);
    void deleteTeacher(Teacher teacher);
    Teacher findByEgn(String egn);
}
