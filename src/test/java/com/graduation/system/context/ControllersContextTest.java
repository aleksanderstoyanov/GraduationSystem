package com.graduation.system.context;

import com.graduation.system.controllers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ControllersContextTest {

    @Autowired
    private HomeController homeController;

    @Autowired
    private AdminController adminController;

    @Autowired
    private AuthController authController;

    @Autowired
    private ApplicationsController applicationsController;

    @Autowired
    private ReviewsController reviewsController;

    @Autowired
    private TeacherController teacherController;

    @Test
    public void assert_That_Home_Controller_Is_Not_Null(){
        assertThat(homeController).isNotNull();
    }

    @Test
    public void assert_That_Admin_Controller_Is_Not_Null(){
        assertThat(adminController).isNotNull();
    }

    @Test
    public void assert_That_Auth_Controller_Is_Not_Null(){
        assertThat(authController).isNotNull();
    }

    @Test
    public void assert_That_Applications_Controller_Is_Not_Null(){
        assertThat(applicationsController).isNotNull();
    }

    @Test
    public void assert_That_Reviews_Controller_Is_Not_Null(){
        assertThat(reviewsController).isNotNull();
    }

    @Test
    public void assert_That_Teacher_Controller_Is_Not_Null(){
        assertThat(teacherController).isNotNull();
    }
}
