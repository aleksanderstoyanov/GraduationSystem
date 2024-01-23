package com.graduation.system.services;


import com.graduation.system.builders.faculty.FacultyBuilder;
import com.graduation.system.builders.review.ReviewBuilder;
import com.graduation.system.builders.review.ReviewDTOBuilder;
import com.graduation.system.data.dto.ReviewDTO;
import com.graduation.system.data.entity.Faculty;
import com.graduation.system.data.entity.Review;
import com.graduation.system.data.repository.ReviewRepository;
import com.graduation.system.services.contracts.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReviewServiceTest {
    @MockBean
    ReviewRepository reviewRepository;

    @Autowired
    ReviewService reviewService;

    Review review;

    @BeforeEach()
    public void setup(){
        ReviewBuilder builder = new ReviewBuilder();
        builder.text("Some Test Review");
        builder.summary("Some Test Summary");

        review = builder.getReview();
    }

    @Test
    public void get_By_Id_Should_Return_Record(){
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        ReviewDTO foundReview = reviewService.getById(1L);

        assertThat(foundReview).isNotNull();
    }
}
