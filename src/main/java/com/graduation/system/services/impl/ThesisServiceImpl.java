package com.graduation.system.services.impl;

import com.graduation.system.dto.ThesisCreateDTO;
import com.graduation.system.dto.ThesisEditDTO;
import com.graduation.system.entity.Application;
import com.graduation.system.entity.Review;
import com.graduation.system.entity.Thesis;
import com.graduation.system.entity.User;
import com.graduation.system.enums.UserRole;
import com.graduation.system.repository.ThesisRepository;
import com.graduation.system.services.contracts.ApplicationService;
import com.graduation.system.services.contracts.ThesisService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ThesisServiceImpl implements ThesisService {
    @Autowired
    private ThesisRepository _repository;
    @Autowired
    private CustomUserDetailsServiceImpl _userService;
    @Autowired
    private ApplicationServiceImpl _applicationService;

    @Override
    public List<Thesis> getStudentTheses(String email) {
        User user = _userService.findByEmail(email);

        if (user == null){
            throw new IllegalArgumentException();
        }

        return _repository.findAll()
                .stream()
                .filter(thesis -> thesis.getApplication() != null)
                .filter(thesis -> thesis.getApplication().getStudent() != null)
                .filter(thesis -> thesis.getApplication().getStudent().getUser().getEmail() == user.getEmail())
                .collect(Collectors.toList());
    }

    @Override
    public void mapReview(Thesis thesis, Review review) {
        thesis.setReview(review);
        _repository.save(thesis);
    }

    @Override
    public List<Thesis> getStudentThesesByFaculty(String email) {
        User user = _userService.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException();
        }


        List<Thesis> theses = _repository.findAll()
                .stream()
                .filter(thesis -> thesis.getApplication() != null)
                .filter(thesis -> thesis.getApplication().getStudent() != null)
                .filter(thesis -> thesis.getApplication().getStudent().getUser().getFaculty() == user.getFaculty())
                .collect(Collectors.toList());

        return theses;
    }

    @Override
    public void createThesis(ThesisCreateDTO createDTO, Long applicationId) {
        Application application = _applicationService.getApplicationById(applicationId);

        if (application == null) {
            throw new IllegalArgumentException();
        }

        Thesis thesis = new Thesis();

        thesis.setText(createDTO.getThesisText());
        thesis.setTitle(createDTO.getThesisTitle());
        thesis.setApplication(application);

        _repository.save(thesis);
    }

    @Override
    public void updateThesis(ThesisEditDTO editDTO, Long id) {
        Thesis thesis = getStudentThesisById(id);

        if(thesis == null){
            throw new IllegalArgumentException();
        }

        thesis.setText(editDTO.getThesisText());
        thesis.setTitle(editDTO.getThesisTitle());

        _repository.save(thesis);
    }

    @Override
    public void deleteThesis(Long id) {
        Thesis thesis = getStudentThesisById(id);

        if(thesis == null){
            throw new IllegalArgumentException();
        }

        _repository.delete(thesis);
    }

    @Override
    public Thesis getStudentThesisById(Long id) {
        return _repository.findById(id).get();
    }
}
