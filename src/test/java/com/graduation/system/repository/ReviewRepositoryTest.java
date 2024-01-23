package com.graduation.system.repository;


import com.graduation.system.builders.review.ReviewBuilder;
import com.graduation.system.data.entity.Review;
import com.graduation.system.data.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    ReviewRepository reviewRepository;

    Review review;

    @BeforeEach
    public void setup(){
        ReviewBuilder builder = new ReviewBuilder();

        builder.text("Some Test Review Text");
        builder.summary("Some Test Review Summary");

        review = builder.getReview();

        testEntityManager.persistAndFlush(review);
    }

    @Test
    public void get_By_Id_Should_Return_Record(){
        assertThat(reviewRepository.findById(1L)).isNotNull();
    }
}
