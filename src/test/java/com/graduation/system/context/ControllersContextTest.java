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
    public void assertThatHomeControllerIsNotNull(){
        assertThat(homeController).isNotNull();
    }

    @Test
    public void assertThatAdminControllerIsNot(){
        assertThat(adminController).isNotNull();
    }
}
