package com.graduation.system.services.contracts;

import com.graduation.system.dto.ApplicationDTO;
import com.graduation.system.entity.Application;

import java.util.List;

public interface ApplicationService {

    void createStudentApplication(ApplicationDTO dto, String username);
    void updateApplication(ApplicationDTO dto);
    void updateStudentApplication(ApplicationDTO dto, Long id);
    void deleteStudentApplication(Long id);
    ApplicationDTO getApplicationById(Long id);
    List<ApplicationDTO> getStudentApplicationsByFaculty(String email);
    List<ApplicationDTO> getStudentApplications(String email);

}
