package com.graduation.system.context;

import com.graduation.system.data.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RepositoryContextTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void assert_That_User_Repository_Is_Not_Null(){
        assertThat(userRepository).isNotNull();
    }

    @Test
    public void assert_That_Faculty_Repository_Is_Not_Null(){
        assertThat(facultyRepository).isNotNull();
    }

    @Test
    public void assert_That_Role_Repository_Is_Not_Null(){
        assertThat(roleRepository).isNotNull();
    }

    @Test
    public void assert_That_Review_Repository_Is_Not_Null(){
        assertThat(reviewRepository).isNotNull();
    }

    @Test
    public void assert_That_Student_Repository_Is_Not_Null(){
        assertThat(studentRepository).isNotNull();
    }
}
