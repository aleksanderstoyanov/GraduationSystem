package com.graduation.system.services.contracts;

import com.graduation.system.data.dto.ReviewDTO;
import com.graduation.system.data.dto.ThesisDTO;

import java.util.List;

public interface ThesisService {
    List<ThesisDTO> getStudentTheses(String email);
    void mapReview(ThesisDTO thesis, ReviewDTO review);
    List<ThesisDTO> getStudentThesesByFaculty(String email);
    void createThesis(ThesisDTO createDTO, Long applicationId);
    void updateThesis(ThesisDTO editDTO, Long id);
    void deleteThesis(Long id);
    ThesisDTO getStudentThesisById(Long id);
}
