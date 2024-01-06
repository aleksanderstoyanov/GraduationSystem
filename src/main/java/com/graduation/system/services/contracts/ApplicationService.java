package com.graduation.system.services.contracts;

import com.graduation.system.dto.ApplicationCreateDTO;
import com.graduation.system.dto.ApplicationEditDTO;
import com.graduation.system.entity.Application;

import java.util.List;

public interface ApplicationService {

    void createStudentApplication(ApplicationCreateDTO applicationCreateDTO, String username);
    void updateStudentApplication(ApplicationEditDTO applicationEditDTO, Long id);
    void deleteStudentApplication(Long id);
    Application getApplicationById(Long id);

    List<Application> getStudentApplicationsByFaculty(String email);
    List<Application> getStudentApplications(String email);

}
