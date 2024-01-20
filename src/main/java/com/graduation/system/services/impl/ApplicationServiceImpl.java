package com.graduation.system.services.impl;

import com.graduation.system.data.dto.ApplicationDTO;
import com.graduation.system.data.dto.StudentDTO;
import com.graduation.system.data.dto.UserDTO;
import com.graduation.system.data.entity.Application;
import com.graduation.system.data.entity.Student;
import com.graduation.system.data.entity.Teacher;
import com.graduation.system.exceptions.ApplicationNotFoundException;
import com.graduation.system.exceptions.StudentNotFoundException;
import com.graduation.system.mapping.ApplicationModelMapper;
import com.graduation.system.data.repository.ApplicationRepository;
import com.graduation.system.services.contracts.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.graduation.system.messages.ErrorMessages.StudentErrorMessages;

import static com.graduation.system.messages.ErrorMessages.ApplicationErrorMessages;
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

        if (user.getStudentDTO() == null) {
            throw new StudentNotFoundException(StudentErrorMessages.StudentNotFound);
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
        Application application = _repository.findById(dto.getId())
                .orElseThrow(() -> new ApplicationNotFoundException(ApplicationErrorMessages.ApplicationNotFound));

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
        Application application = _repository.findById(dto.getId())
                .orElseThrow(() -> new ApplicationNotFoundException(ApplicationErrorMessages.ApplicationNotFound));

        application.setTask(dto.getTask());
        application.setSubject(dto.getSubject());
        application.setPurpose(dto.getSubject());

        _repository.save(application);
    }

    @Override
    public void deleteStudentApplication(Long id) {
        Application application = _repository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException(ApplicationErrorMessages.ApplicationNotFound));

        _repository.delete(application);
    }

    @Override
    public ApplicationDTO getApplicationById(Long id) {
        return (ApplicationDTO)_mapper.mapToModel(_repository
                .findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException(
                        ApplicationErrorMessages.ApplicationNotFound)
                ), ApplicationDTO.class);
    }

    @Override
    public List<ApplicationDTO> getStudentApplicationsByFaculty(String email) {
        UserDTO user = _userService.findByEmail(email);

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

        if (user.getStudentDTO() == null) {
            throw new StudentNotFoundException(StudentErrorMessages.StudentNotFound);
        }

        StudentDTO student = user.getStudentDTO();

        return _repository.findAll().stream()
                .filter(application -> application.getStudent().getEgn() == student.getEgn())
                .map(application -> (ApplicationDTO)_mapper.mapToModel(application, ApplicationDTO.class))
                .collect(Collectors.toList());
    }
}
