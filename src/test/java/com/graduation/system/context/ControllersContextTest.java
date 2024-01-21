package com.graduation.system.context;

import com.graduation.system.controllers.AdminController;
import com.graduation.system.controllers.HomeController;
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

    @Test
    public void assert_That_Home_Controller_Is_Not_Null(){
        assertThat(homeController).isNotNull();
    }

    @Test
    public void assert_That_Admin_Controller_Is_Not_Null(){
        assertThat(adminController).isNotNull();
    }
}
