package com.graduation.system.controllers;

import com.graduation.system.SystemApplication;
import com.graduation.system.config.SecurityConfig;
import com.graduation.system.services.impl.CustomUserDetailsServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(HomeController.class)
@Import(SecurityConfig.class)
public class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void home_Page_Should_Return_Default_Greeting_Message() throws Exception {
        // Arrange
        RequestBuilder request = get("/");
        ResultMatcher matcher = content().string(containsString("Hello anonymousUser!"));

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @WithMockUser(username = "test", password = "123123", roles = "USER")
    public void home_Page_Should_Return_User_Greeting_Message() throws Exception{
        // Arrange
        RequestBuilder request = get("/");
        ResultMatcher matcher = content().string(containsString("Hello test!"));

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }
}
