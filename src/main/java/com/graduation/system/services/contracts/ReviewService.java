package com.graduation.system.services.contracts;

import com.graduation.system.dto.CreateReviewDTO;
import com.graduation.system.entity.Review;
import com.graduation.system.entity.Thesis;

public interface ReviewService {

    Review getById(Long id);
    void createReview(CreateReviewDTO createDto, Long thesisId);
}
