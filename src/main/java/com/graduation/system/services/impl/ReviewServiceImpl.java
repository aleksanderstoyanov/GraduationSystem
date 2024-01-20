package com.graduation.system.services.impl;

import com.graduation.system.data.dto.ReviewDTO;
import com.graduation.system.data.dto.ThesisDTO;
import com.graduation.system.data.entity.Review;
import com.graduation.system.data.entity.Thesis;
import com.graduation.system.exceptions.ReviewNotFoundException;
import com.graduation.system.mapping.ReviewModelMapper;
import com.graduation.system.data.repository.ReviewRepository;
import com.graduation.system.services.contracts.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.graduation.system.messages.ErrorMessages.ReviewErrorMessages;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewModelMapper _mapper;
    @Autowired
    private ReviewRepository _repository;
    @Autowired
    private ThesisServiceImpl _thesisService;

    @Override
    public Review getById(Long id){
        return _repository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(ReviewErrorMessages.ReviewNotFound));
    }
    @Override
    public void createReview(ReviewDTO createDto, Long thesisId) {
        ThesisDTO thesis = _thesisService.getStudentThesisById(thesisId);

        Review review = new Review();

        review.setText(createDto.getText());
        review.setSummary(createDto.getSummary());
        review.setGranted(createDto.isGranted());
        review.setThesis((Thesis) _mapper.mapToModel(thesis, Thesis.class));

        _repository.save(review);
        _thesisService.mapReview(thesis, (ReviewDTO) _mapper.mapToModel(review, ReviewDTO.class));
    }
}
