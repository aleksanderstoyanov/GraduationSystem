package com.graduation.system.builders.faculty;

import com.graduation.system.data.entity.Faculty;

public class FacultyBuilder {

    private Faculty faculty;

    public FacultyBuilder(){
        this.faculty = new Faculty();
    }

    public FacultyBuilder name(String name){
        this.faculty.setName(name);
        return this;
    }

    public void resetFaculty(){
        this.faculty = new Faculty();
    }

    public Faculty getFaculty(){
        return this.faculty;
    }
}
