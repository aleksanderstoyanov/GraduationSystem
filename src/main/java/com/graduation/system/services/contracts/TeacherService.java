package com.graduation.system.services.contracts;

import com.graduation.system.dto.TeacherDTO;
import com.graduation.system.entity.Teacher;
import com.graduation.system.enums.Position;

public interface TeacherService {
    Position getPositionByName(String name);
    void headApplication (Long id, String email);
    void approveApplication(Long id, String email);
    void disapproveApplication(Long id, String email);
    void createTeacher(TeacherDTO teacher);
    void deleteTeacher(TeacherDTO teacher);
    TeacherDTO findByEgn(String egn);
}
