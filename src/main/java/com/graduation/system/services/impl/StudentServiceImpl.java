package com.graduation.system.services.impl;


import com.graduation.system.data.entity.Faculty;
import com.graduation.system.data.entity.Student;
import com.graduation.system.data.entity.User;
import com.graduation.system.data.repository.StudentRepository;
import com.graduation.system.services.contracts.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private FacultyServiceImpl _facultyService;
    private StudentRepository _repository;

    public List<Student> getStudents() {
        return _repository.getAll();
    }

    public String generateFacultyNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append("F");

        for (int i = 0; i < 6; i++) {
            new Random().nextInt(1, 9);
            sb.append(new Random().nextInt(1, 9));
        }

        return sb.toString();

    }

    public void createStudent(Student student) {
        _repository.save(student);
    }

    public void deleteStudent(Student student) {
        _repository.delete(student);
    }

    public Student findByEgn(String egn) {
        return _repository.findByEgn(egn);
    }

    public void createStudent(String facultyName, String firstName, String lastName, User user) {
        Student student = new Student();
        Faculty faculty = _facultyService.getByName(facultyName);

        if (faculty == null) {
            return;
        }

        String fn = generateFacultyNumber();
        student.setFn(fn);

        _repository.save(student);
    }
}
