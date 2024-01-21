package com.graduation.system.builders.application;

import com.graduation.system.models.ApplicationViewModel;

public class ApplicationViewModelBuilder {
    private ApplicationViewModel application;

    public ApplicationViewModelBuilder(){
        this.application = new ApplicationViewModel();
    }

    public ApplicationViewModelBuilder id(Long id){
        this.application.setId(id);
        return this;
    }

    public ApplicationViewModelBuilder subject(String subject){
        this.application.setSubject(subject);
        return this;
    }

    public ApplicationViewModelBuilder task(String task){
        this.application.setTask(task);
        return this;
    }

    public ApplicationViewModelBuilder purpose(String purpose){
        this.application.setPurpose(purpose);
        return this;
    }

    public ApplicationViewModel getApplication(){
        return this.application;
    }

    public void resetApplication(){
        application = new ApplicationViewModel();
    }
}
