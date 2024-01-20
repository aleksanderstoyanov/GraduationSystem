package com.graduation.system.controllers;


import com.graduation.system.data.entity.Student;
import com.graduation.system.services.impl.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@AllArgsConstructor
public class StudentsController {
    @Autowired
    private StudentServiceImpl _studentService;

    @RequestMapping(value = "/students", method = RequestMethod.GET )
    public String index (Model model){

//        Student student = new Student();
//
//        student.setFn("F099867");
//        student.setName("Pesho");
//
//        _studentService.createStudent(student);

        List<Student> students = _studentService.getStudents();

        model.addAttribute("students", students);
        model.addAttribute("message", "All Students");

        return "students/index.html";
    }
}
