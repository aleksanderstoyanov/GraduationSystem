package com.graduation.system.builders.student;

import com.graduation.system.data.entity.Student;

public class StudentBuilder {
    private Student student;

    public StudentBuilder(){
        this.student = new Student();
    }

    public StudentBuilder fn(String fn){
        this.student.setFn(fn);
        return this;
    }

    public StudentBuilder egn(String egn){
        this.student.setEgn(egn);
        return this;
    }

    public void resetStudent(){
        this.student = new Student();
    }

    public Student getStudent(){
        return this.student;
    }



}
