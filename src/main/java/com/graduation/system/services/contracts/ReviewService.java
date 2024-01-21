package com.graduation.system.services.contracts;

import com.graduation.system.data.dto.ReviewDTO;
import com.graduation.system.data.entity.Review;

public interface ReviewService {

    ReviewDTO getById(Long id);
    void createReview(ReviewDTO createDto, Long thesisId);
}
