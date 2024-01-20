package com.graduation.system.data.repository;

import com.graduation.system.data.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
}
