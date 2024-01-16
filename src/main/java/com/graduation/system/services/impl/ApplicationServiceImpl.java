package com.graduation.system.services.impl;

import com.graduation.system.dto.ApplicationDTO;
import com.graduation.system.dto.StudentDTO;
import com.graduation.system.dto.UserDTO;
import com.graduation.system.entity.Application;
import com.graduation.system.entity.Student;
import com.graduation.system.entity.Teacher;
import com.graduation.system.entity.User;
import com.graduation.system.mapping.ApplicationModelMapper;
import com.graduation.system.repository.ApplicationRepository;
import com.graduation.system.services.contracts.ApplicationService;
import com.graduation.system.services.contracts.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationModelMapper _mapper;
    private ApplicationRepository _repository;
    private CustomUserDetailsServiceImpl _userService;

    @Override
    public void createStudentApplication(ApplicationDTO dto, String email) {
        UserDTO user = _userService.findByEmail(email);

        if (user == null || user.getStudentDTO() == null) {
            throw new IllegalArgumentException();
        }

        StudentDTO student = user.getStudentDTO();

        Application application = new Application();

        application.setTask(dto.getTask());
        application.setPurpose(dto.getPurpose());
        application.setSubject(dto.getSubject());

        application.setStudent((Student) _mapper.mapToModel(student, Student.class));

        _repository.save(application);
    }

    @Override
    public void updateApplication(ApplicationDTO dto) {
        Application application = _repository.findById(dto.getId()).get();

        if (application == null){
            throw new IllegalArgumentException();
        }

        application.setTask(dto.getTask());
        application.setSubject(dto.getSubject());
        application.setPurpose(dto.getSubject());
        application.setApproved(dto.isApproved());

        if (dto.getTeacher() != null){
            application.setTeacher((Teacher) _mapper.mapToModel(dto.getTeacher(), Teacher.class));
        }

        _repository.save(application);
    }

    @Override
    public void updateStudentApplication(ApplicationDTO dto, Long id) {
        Application application = _repository.findById(dto.getId()).get();

        if (application == null){
            throw new IllegalArgumentException();
        }

        application.setTask(dto.getTask());
        application.setSubject(dto.getSubject());
        application.setPurpose(dto.getSubject());

        _repository.save(application);
    }

    @Override
    public void deleteStudentApplication(Long id) {
        Application application = _repository.findById(id).get();

        if (application == null){
            throw new IllegalArgumentException();
        }

        _repository.delete(application);
    }

    @Override
    public ApplicationDTO getApplicationById(Long id) {
        return (ApplicationDTO)_mapper.mapToModel(_repository.findById(id), ApplicationDTO.class);
    }

    @Override
    public List<ApplicationDTO> getStudentApplicationsByFaculty(String email) {
        UserDTO user = _userService.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException();
        }

        List<ApplicationDTO> applications = _repository.findAll()
                .stream()
                .filter(application -> application.getStudent() != null)
                .filter(application -> application.getStudent().getUser().getFaculty() == user.getFaculty())
                .map((application -> (ApplicationDTO) _mapper.mapToModel(application, ApplicationDTO.class)))
                .collect(Collectors.toList());

        return applications;
    }

    @Override
    public List<ApplicationDTO> getStudentApplications(String email) {
        UserDTO user = _userService.findByEmail(email);

        if (user == null || user.getStudentDTO() == null) {
            throw new IllegalArgumentException();
        }

        StudentDTO student = user.getStudentDTO();

        return _repository.findAll().stream()
                .filter(application -> application.getStudent().getEgn() == student.getEgn())
                .map(application -> (ApplicationDTO)_mapper.mapToModel(application, ApplicationDTO.class))
                .collect(Collectors.toList());
    }
}
