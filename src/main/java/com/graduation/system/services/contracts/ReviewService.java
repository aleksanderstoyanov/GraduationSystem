package com.graduation.system.services.contracts;

import com.graduation.system.dto.ReviewDTO;
import com.graduation.system.entity.Review;

public interface ReviewService {

    Review getById(Long id);
    void createReview(ReviewDTO createDto, Long thesisId);
}
