package com.graduation.system.services.impl;

import com.graduation.system.data.dto.ApplicationDTO;
import com.graduation.system.data.dto.TeacherDTO;
import com.graduation.system.data.dto.UserDTO;
import com.graduation.system.data.entity.Teacher;
import com.graduation.system.data.enums.Position;
import com.graduation.system.mapping.ApplicationModelMapper;
import com.graduation.system.mapping.TeacherModelMapper;
import com.graduation.system.data.repository.TeacherRepository;
import com.graduation.system.services.contracts.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherModelMapper _teacherMapper;
    @Autowired
    private ApplicationModelMapper _applicationMapper;

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
        UserDTO user = _userService.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException();
        }

        TeacherDTO teacher = (TeacherDTO) _teacherMapper.mapToModel(user.getTeacherDTO(), TeacherDTO.class);
        ApplicationDTO application = _applicationService.getApplicationById(id);

        if (teacher == null || application == null){
            throw new IllegalArgumentException();
        }

        application.setTeacher(teacher);
        _applicationService.updateApplication(application);

        _applicationService.updateApplication((ApplicationDTO) _applicationMapper
                .mapToModel(application, ApplicationDTO.class)
        );
    }

    @Override
    public void approveApplication (Long id, String email) {
        UserDTO user = _userService.findByEmail(email);

        TeacherDTO teacher = user.getTeacherDTO();
        ApplicationDTO application = _applicationService.getApplicationById(id);

        application.setApproved(true);
        _applicationService.updateApplication((ApplicationDTO) _applicationMapper
                .mapToModel(application, ApplicationDTO.class)
        );
    }

    @Override
    public void disapproveApplication (Long id, String email) {
        UserDTO user = _userService.findByEmail(email);

        TeacherDTO teacher = user.getTeacherDTO();
        ApplicationDTO application = _applicationService.getApplicationById(id);

        if (teacher == null || application == null){
            throw new IllegalArgumentException();
        }

        application.setApproved(false);
        _applicationService.updateApplication((ApplicationDTO) _applicationMapper
                .mapToModel(application, ApplicationDTO.class)
        );
    }

    @Override
    public TeacherDTO findByEgn(String egn) {

        if (_repository.findByEgn(egn) == null){
            return null;
        }

        return (TeacherDTO) _teacherMapper
                .mapToModel(_repository.findByEgn(egn), TeacherDTO.class);
    }

    @Override
    public void createTeacher(TeacherDTO dto) {
        Teacher teacher = (Teacher) _teacherMapper
                .mapToModel(dto, Teacher.class);

        _repository.save(teacher);
    }

    @Override
    public void deleteTeacher(TeacherDTO dto){
        Teacher teacher = _repository.findById(dto.getId()).get();
        _repository.delete(teacher);
    }
}
