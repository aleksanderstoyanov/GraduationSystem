package com.graduation.system.builders.review;

import com.graduation.system.data.entity.Review;

public class ReviewBuilder {
    private Review review;

    public ReviewBuilder(){
        review = new Review();
    }

    public ReviewBuilder text(String text){
        this.review.setText(text);
        return this;
    }

    public ReviewBuilder summary(String summary){
        this.review.setSummary(summary);
        return this;
    }

    public void resetReview(){
        this.review = new Review();
    }

    public Review getReview(){
        return this.review;
    }
}
