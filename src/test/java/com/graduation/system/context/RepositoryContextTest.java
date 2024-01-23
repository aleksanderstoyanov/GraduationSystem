package com.graduation.system.context;

import com.graduation.system.data.repository.FacultyRepository;
import com.graduation.system.data.repository.RoleRepository;
import com.graduation.system.data.repository.UserRepository;
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
        assertThat(facultyRepository).isNotNull();
    }
}
