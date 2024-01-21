package com.graduation.system.builders.application;

import com.graduation.system.models.ApplicationCreateViewModel;
import com.graduation.system.models.ApplicationEditViewModel;

public class ApplicationCreateViewModelBuilder {
    private ApplicationCreateViewModel application;

    public ApplicationCreateViewModelBuilder(){
        this.application = new ApplicationCreateViewModel();
    }

    public ApplicationCreateViewModelBuilder subject(String subject){
        this.application.setSubject(subject);
        return this;
    }

    public ApplicationCreateViewModelBuilder task(String task){
        this.application.setTask(task);
        return this;
    }

    public ApplicationCreateViewModelBuilder purpose(String purpose){
        this.application.setPurpose(purpose);
        return this;
    }

    public ApplicationCreateViewModel getApplication(){
        return this.application;
    }

    public void resetApplication(){
        application = new ApplicationCreateViewModel();
    }
}
