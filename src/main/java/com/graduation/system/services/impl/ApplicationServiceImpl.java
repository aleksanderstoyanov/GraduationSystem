package com.graduation.system.services.impl;

import com.graduation.system.dto.ApplicationCreateDTO;
import com.graduation.system.dto.ApplicationEditDTO;
import com.graduation.system.entity.Application;
import com.graduation.system.entity.Student;
import com.graduation.system.entity.User;
import com.graduation.system.repository.ApplicationRepository;
import com.graduation.system.services.contracts.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository _repository;
    private CustomUserDetailsServiceImpl _userService;

    @Override
    public void createStudentApplication(ApplicationCreateDTO applicationCreateDTO, String email) {
        User user = _userService.findByEmail(email);

        if (user == null || user.getStudent() == null) {
            throw new IllegalArgumentException();
        }
        Student student = user.getStudent();

        Application application = new Application();

        application.setTask(applicationCreateDTO.getTask());
        application.setPurpose(applicationCreateDTO.getPurpose());
        application.setSubject(applicationCreateDTO.getSubject());
        application.setStudent(student);

        _repository.save(application);
    }

    @Override
    public void updateStudentApplication(ApplicationEditDTO applicationEditDTO, Long id) {
        Application application = getApplicationById(id);

        if (application == null){
            throw new IllegalArgumentException();
        }

        application.setTask(applicationEditDTO.getTask());
        application.setSubject(applicationEditDTO.getSubject());
        application.setPurpose(applicationEditDTO.getSubject());

        _repository.save(application);
    }

    @Override
    public void deleteStudentApplication(Long id) {
        Application application = getApplicationById(id);

        if (application == null){
            throw new IllegalArgumentException();
        }

        _repository.delete(application);
    }

    @Override
    public Application getApplicationById(Long id) {
        return _repository.findById(id).get();
    }

    @Override
    public List<Application> getStudentApplicationsByFaculty(String email) {
        User user = _userService.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException();
        }

        List<Application> applications = _repository.findAll()
                .stream()
                .filter(application -> application.getStudent() != null)
                .filter(application -> application.getStudent().getUser().getFaculty() == user.getFaculty())
                .collect(Collectors.toList());

        return applications;
    }

    @Override
    public List<Application> getStudentApplications(String email) {
        User user = _userService.findByEmail(email);

        if (user == null || user.getStudent() == null) {
            throw new IllegalArgumentException();
        }

        Student student = user.getStudent();

        return _repository.findAll().stream()
                .filter(application -> application.getStudent().getEgn() == student.getEgn())
                .collect(Collectors.toList());
    }
}
