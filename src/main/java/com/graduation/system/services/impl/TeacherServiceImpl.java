package com.graduation.system.services.impl;

import com.graduation.system.entity.Student;
import com.graduation.system.entity.Teacher;
import com.graduation.system.enums.Position;
import com.graduation.system.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherServiceImpl {

    @Autowired
    private TeacherRepository _repository;
    public Position getPositionByName(String name){
        Position position = Position.valueOf(name);
        return position;
    }

    public Teacher findByEgn(String egn) {
        return _repository.findByEgn(egn);
    }

    public void createTeacher(Teacher teacher) {
        _repository.save(teacher);
    }

    public void deleteTeacher(Teacher teacher){
        _repository.delete(teacher);
    }
}
