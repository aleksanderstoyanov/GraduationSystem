package com.graduation.system.repository;

import com.graduation.system.builders.faculty.FacultyBuilder;
import com.graduation.system.data.entity.Faculty;
import com.graduation.system.data.repository.FacultyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FacultyRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    FacultyRepository facultyRepository;

    Faculty faculty;

    @BeforeEach
    public void setup(){
        FacultyBuilder builder = new FacultyBuilder();

        builder.name("Test");

        faculty = builder.getFaculty();

        this.testEntityManager.persistAndFlush(faculty);
    }

    @Test
    public void find_By_Name_Should_Return_Record(){
        assertThat(facultyRepository.findByName("Test").stream().count()).isEqualTo(1);
    }
}
