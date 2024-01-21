package com.graduation.system.builders.review;

import com.graduation.system.data.dto.ReviewDTO;
import com.graduation.system.models.ReviewCreateViewModel;
import com.graduation.system.models.ReviewViewModel;

public class ReviewDTOBuilder {
    private ReviewDTO review;

    public ReviewDTOBuilder(){
        this.review = new ReviewDTO();
    }

    public ReviewDTOBuilder id(Long id){
        this.review.setId(id);
        return this;
    }
    public ReviewDTOBuilder text(String text){
        this.review.setText(text);
        return this;
    }

    public ReviewDTOBuilder summary(String summary){
        this.review.setSummary(summary);
        return this;
    }

    public ReviewDTOBuilder isGranted(boolean isGranted){
        this.review.setGranted(isGranted);
        return this;
    }

    public ReviewDTO getReview(){
        return this.review;
    }

    public void resetReview(){
        this.review = new ReviewDTO();
    }
}
