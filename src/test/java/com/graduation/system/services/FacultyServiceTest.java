package com.graduation.system.services;

import com.graduation.system.builders.faculty.FacultyBuilder;
import com.graduation.system.data.entity.Faculty;
import com.graduation.system.data.repository.FacultyRepository;
import com.graduation.system.services.contracts.FacultyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FacultyServiceTest {

    @MockBean
    FacultyRepository facultyRepository;

    @Autowired
    FacultyService facultyService;

    Faculty faculty;

    @BeforeEach()
    public void setup(){
        FacultyBuilder builder = new FacultyBuilder();
        builder.name("Test");
        faculty = builder.getFaculty();
    }
    @Test
    public void get_By_Name_Should_Return_Record(){
        when(facultyRepository.findByName("Test")).thenReturn(Optional.of(faculty));

        Faculty foundFaculty = facultyService.getByName("Test");

        assertThat(foundFaculty.getName()).isEqualTo("Test");
    }

    @Test
    public void create_Should_Create_Record(){
        when(facultyRepository.findByName("Test")).thenReturn(Optional.of(faculty));
        facultyService.create("Test");

        Faculty foundFaculty = facultyService.getByName("Test");
        assertThat(foundFaculty.getName()).isEqualTo("Test");
    }
}
