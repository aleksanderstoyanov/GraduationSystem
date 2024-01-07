package com.graduation.system.services.contracts;

import com.graduation.system.dto.ThesisCreateDTO;
import com.graduation.system.dto.ThesisEditDTO;
import com.graduation.system.entity.Thesis;

import java.util.List;

public interface ThesisService {
    List<Thesis> getStudentTheses(String email);
    void createThesis(ThesisCreateDTO createDTO, Long applicationId);
    void updateThesis(ThesisEditDTO editDTO, Long id);

    void deleteThesis(Long id);
    Thesis getStudentThesisById(Long id);
}
