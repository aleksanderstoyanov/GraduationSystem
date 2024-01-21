package com.graduation.system.builders.application;

import com.graduation.system.data.dto.ApplicationDTO;

public class ApplicationDTOBuilder {

    private ApplicationDTO application;

    public ApplicationDTOBuilder(){
        this.application = new ApplicationDTO();
    }

    public ApplicationDTOBuilder id(Long id){
        this.application.setId(id);
        return this;
    }

    public ApplicationDTOBuilder subject(String subject){
        this.application.setSubject(subject);
        return this;
    }
    public ApplicationDTOBuilder task(String task){
        this.application.setTask(task);
        return this;
    }
    public ApplicationDTOBuilder purpose(String purpose){
        this.application.setPurpose(purpose);
        return this;
    }

    public ApplicationDTO getApplication(){
        return this.application;
    }

    public void resetApplication(){
        application = new ApplicationDTO();
    }
}
