package com.graduation.system.services.impl;

import com.graduation.system.entity.Application;
import com.graduation.system.entity.Student;
import com.graduation.system.entity.Teacher;
import com.graduation.system.entity.User;
import com.graduation.system.enums.Position;
import com.graduation.system.repository.TeacherRepository;
import com.graduation.system.services.contracts.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private CustomUserDetailsServiceImpl _userService;

    @Autowired
    private ApplicationServiceImpl _applicationService;
    @Autowired
    private TeacherRepository _repository;

    @Override
    public Position getPositionByName(String name){
        Position position = Position.valueOf(name);
        return position;
    }

    @Override
    public void headApplication (Long id, String email) {
        User user = _userService.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException();
        }

        Teacher teacher = user.getTeacher();
        Application application = _applicationService.getApplicationById(id);

        if (teacher == null || application == null){
            throw new IllegalArgumentException();
        }

        application.setTeacher(teacher);
        _applicationService.updateApplication(application);
    }

    @Override
    public void approveApplication (Long id, String email) {
        User user = _userService.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException();
        }

        Teacher teacher = user.getTeacher();
        Application application = _applicationService.getApplicationById(id);

        if (teacher == null || application == null){
            throw new IllegalArgumentException();
        }

        application.setApproved(true);
        _applicationService.updateApplication(application);
    }

    @Override
    public void disapproveApplication (Long id, String email) {
        User user = _userService.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException();
        }

        Teacher teacher = user.getTeacher();
        Application application = _applicationService.getApplicationById(id);

        if (teacher == null || application == null){
            throw new IllegalArgumentException();
        }

        application.setApproved(false);
        _applicationService.updateApplication(application);
    }

    @Override
    public Teacher findByEgn(String egn) {
        return _repository.findByEgn(egn);
    }

    @Override
    public void createTeacher(Teacher teacher) {
        _repository.save(teacher);
    }

    @Override
    public void deleteTeacher(Teacher teacher){
        _repository.delete(teacher);
    }
}
