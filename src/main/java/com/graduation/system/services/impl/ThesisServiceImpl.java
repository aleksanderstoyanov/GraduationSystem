package com.graduation.system.services.impl;

import com.graduation.system.dto.*;
import com.graduation.system.entity.Application;
import com.graduation.system.entity.Review;
import com.graduation.system.entity.Thesis;
import com.graduation.system.entity.User;
import com.graduation.system.enums.UserRole;
import com.graduation.system.mapping.ApplicationModelMapper;
import com.graduation.system.mapping.ThesisModelMapper;
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
    private ThesisModelMapper _mapper;

    @Autowired
    private ApplicationModelMapper _applicationMapper;
    @Autowired
    private CustomUserDetailsServiceImpl _userService;
    @Autowired
    private ApplicationServiceImpl _applicationService;

    @Override
    public List<ThesisDTO> getStudentTheses(String email) {
        UserDTO user = _userService.findByEmail(email);

        if (user == null){
            throw new IllegalArgumentException();
        }

        return _repository.findAll()
                .stream()
                .filter(thesis -> thesis.getApplication() != null)
                .filter(thesis -> thesis.getApplication().getStudent() != null)
                .filter(thesis -> thesis.getApplication().getStudent().getUser().getEmail() == user.getEmail())
                .map(thesis -> (ThesisDTO) _mapper.mapToModel(thesis, ThesisDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void mapReview(ThesisDTO thesisDTO, ReviewDTO reviewDTO) {

        Thesis thesis = (Thesis) _mapper.mapToModel(thesisDTO, Thesis.class);
        Review review = (Review) _mapper.mapToModel(reviewDTO, Review.class);

        thesis.setReview(review);

        _repository.save(thesis);
    }

    @Override
    public List<ThesisDTO> getStudentThesesByFaculty(String email) {
        UserDTO user = _userService.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException();
        }

        return _repository.findAll()
                .stream()
                .filter(thesis -> thesis.getApplication() != null)
                .filter(thesis -> thesis.getApplication().getStudent() != null)
                .filter(thesis -> thesis.getApplication().getStudent().getUser().getFaculty() == user.getFaculty())
                .map(thesis -> (ThesisDTO) _mapper.mapToModel(thesis, ThesisDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createThesis(ThesisDTO createDTO, Long applicationId) {
        ApplicationDTO application = _applicationService.getApplicationById(applicationId);

        if (application == null) {
            throw new IllegalArgumentException();
        }

        Thesis thesis = new Thesis();

        thesis.setText(createDTO.getText());
        thesis.setTitle(createDTO.getTitle());

        thesis.setApplication((Application) _applicationMapper.mapToModel(application, Application.class));

        _repository.save(thesis);
    }

    @Override
    public void updateThesis(ThesisDTO editDTO, Long id) {
        ThesisDTO thesis = getStudentThesisById(id);

        if(thesis == null){
            throw new IllegalArgumentException();
        }

        thesis.setText(editDTO.getText());
        thesis.setTitle(editDTO.getTitle());

        _repository.save((Thesis) _mapper
                .mapToModel(thesis, Thesis.class)
        );
    }

    @Override
    public void deleteThesis(Long id) {
        Thesis thesis = _repository.findById(id).get();

        if(thesis == null){
            throw new IllegalArgumentException();
        }

        _repository.delete(thesis);
    }

    @Override
    public ThesisDTO getStudentThesisById(Long id) {
        return (ThesisDTO) _mapper
                .mapToModel(_repository.findById(id).get(), ThesisDTO.class);
    }
}
