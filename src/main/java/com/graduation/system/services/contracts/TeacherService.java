package com.graduation.system.services.contracts;

import com.graduation.system.entity.Teacher;
import com.graduation.system.enums.Position;

public interface TeacherService {
    Position getPositionByName(String name);
    void headApplication (Long id, String email);
    void approveApplication(Long id, String email);
    void disapproveApplication(Long id, String email);
    void createTeacher(Teacher teacher);
    void deleteTeacher(Teacher teacher);
    Teacher findByEgn(String egn);
}
