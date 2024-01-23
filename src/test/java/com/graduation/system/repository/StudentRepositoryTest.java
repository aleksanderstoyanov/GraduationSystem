package com.graduation.system.repository;

import com.graduation.system.builders.student.StudentBuilder;
import com.graduation.system.data.entity.Student;
import com.graduation.system.data.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    StudentRepository studentRepository;

    Student student;


    @BeforeEach
    public void setup(){
        StudentBuilder builder = new StudentBuilder();

        builder.fn("F099867");
        builder.egn("0141246326");

        student = builder.getStudent();

        entityManager.persistAndFlush(student);
    }

    @Test
    public void find_All_Should_Return_Records(){
        assertThat(studentRepository.getAll().size()).isNotZero();
    }
}
