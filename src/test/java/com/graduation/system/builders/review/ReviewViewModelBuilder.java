package com.graduation.system.builders.review;

import com.graduation.system.models.ReviewCreateViewModel;
import com.graduation.system.models.ReviewViewModel;

public class ReviewViewModelBuilder {
    private ReviewViewModel review;

    public ReviewViewModelBuilder(){
        this.review = new ReviewViewModel();
    }

    public ReviewViewModelBuilder id(Long id){
        this.review.setId(id);
        return this;
    }
    public ReviewViewModelBuilder text(String text){
        this.review.setText(text);
        return this;
    }

    public ReviewViewModelBuilder summary(String summary){
        this.review.setSummary(summary);
        return this;
    }

    public ReviewViewModelBuilder isGranted(boolean isGranted){
        this.review.setGranted(isGranted);
        return this;
    }
    public ReviewViewModel getReview(){
        return this.review;
    }

    public void resetReview(){
        this.review = new ReviewViewModel();
    }
}
