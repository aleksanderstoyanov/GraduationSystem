package com.graduation.system.context;

import com.graduation.system.controllers.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ControllersContext {

    @Autowired
    private HomeController homeController;

    @Test
    public void assertThatHomeControllerIsNotNull(){
        assertThat(homeController).isNotNull();
    }
}
