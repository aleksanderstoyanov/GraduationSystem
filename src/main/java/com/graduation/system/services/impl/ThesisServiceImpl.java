package com.graduation.system.services.impl;

import com.graduation.system.data.dto.ApplicationDTO;
import com.graduation.system.data.dto.ReviewDTO;
import com.graduation.system.data.dto.ThesisDTO;
import com.graduation.system.data.dto.UserDTO;
import com.graduation.system.data.entity.Application;
import com.graduation.system.data.entity.Review;
import com.graduation.system.data.entity.Thesis;
import com.graduation.system.data.repository.ThesisRepository;
import com.graduation.system.exceptions.ThesisNotFoundException;
import com.graduation.system.mapping.ApplicationModelMapper;
import com.graduation.system.mapping.ThesisModelMapper;
import com.graduation.system.services.contracts.ThesisService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.graduation.system.messages.ErrorMessages.ThesisErrorMessages;

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

        Thesis thesis = new Thesis();

        thesis.setText(createDTO.getText());
        thesis.setTitle(createDTO.getTitle());

        thesis.setApplication((Application) _applicationMapper.mapToModel(application, Application.class));

        _repository.save(thesis);
    }

    @Override
    public void updateThesis(ThesisDTO editDTO, Long id) {
        ThesisDTO thesis = getStudentThesisById(id);

        thesis.setText(editDTO.getText());
        thesis.setTitle(editDTO.getTitle());

        _repository.save((Thesis) _mapper
                .mapToModel(thesis, Thesis.class)
        );
    }

    @Override
    public void deleteThesis(Long id) {
        Thesis thesis = _repository.findById(id)
                .orElseThrow(() -> new ThesisNotFoundException(ThesisErrorMessages.ThesisNotFound));

        _repository.delete(thesis);
    }

    @Override
    public ThesisDTO getStudentThesisById(Long id) {

        Thesis thesis = _repository.findById(id)
                .orElseThrow(() -> new ThesisNotFoundException(ThesisErrorMessages.ThesisNotFound));

        return (ThesisDTO) _mapper
                .mapToModel(thesis, ThesisDTO.class);
    }
}
