package com.graduation.system.builders.application;

import com.graduation.system.models.ApplicationEditViewModel;

public class ApplicationEditViewModelBuilder {
    private ApplicationEditViewModel application;

    public ApplicationEditViewModelBuilder(){
        this.application = new ApplicationEditViewModel();
    }

    public ApplicationEditViewModelBuilder id(Long id){
        this.application.setId(id);
        return this;
    }

    public ApplicationEditViewModelBuilder subject(String subject){
        this.application.setSubject(subject);
        return this;
    }

    public ApplicationEditViewModelBuilder task(String task){
        this.application.setTask(task);
        return this;
    }

    public ApplicationEditViewModelBuilder purpose(String purpose){
        this.application.setPurpose(purpose);
        return this;
    }

    public ApplicationEditViewModel getApplication(){
        return this.application;
    }

    public void resetApplication(){
        application = new ApplicationEditViewModel();
    }
}
