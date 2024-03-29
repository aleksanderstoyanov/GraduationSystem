package com.graduation.system.context;

import com.graduation.system.services.contracts.FacultyService;
import com.graduation.system.services.contracts.ReviewService;
import com.graduation.system.services.contracts.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ServicesContextTest {
    @Autowired
    FacultyService facultyService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    RoleService roleService;

    @Test
    public void assert_That_Faculty_Service_Is_Not_Null(){
        assertThat(facultyService).isNotNull();
    }

    @Test
    public void assert_That_Role_Service_Is_Not_Null(){
        assertThat(roleService).isNotNull();
    }
}
