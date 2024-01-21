package com.graduation.system.builders.review;

import com.graduation.system.models.ReviewCreateViewModel;
import com.graduation.system.models.ReviewViewModel;

public class ReviewCreateViewModelBuilder {
    private ReviewCreateViewModel review;

    public ReviewCreateViewModelBuilder(){
        this.review = new ReviewCreateViewModel();
    }

    public ReviewCreateViewModelBuilder text(String text){
        this.review.setText(text);
        return this;
    }

    public ReviewCreateViewModelBuilder summary(String summary){
        this.review.setSummary(summary);
        return this;
    }

    public ReviewCreateViewModelBuilder isGranted(boolean isGranted){
        this.review.setGranted(isGranted);
        return this;
    }

    public ReviewCreateViewModel getReview(){
        return this.review;
    }

    public void resetReview() {
        this.review = new ReviewCreateViewModel();
    }
}
