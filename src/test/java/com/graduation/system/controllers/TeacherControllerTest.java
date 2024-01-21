package com.graduation.system.controllers;


import com.graduation.system.config.SecurityConfig;
import com.graduation.system.services.impl.ApplicationServiceImpl;
import com.graduation.system.services.impl.TeacherServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeacherController.class)
@Import(SecurityConfig.class)
public class TeacherControllerTest {

    @MockBean
    ApplicationServiceImpl applicationService;

    @MockBean
    TeacherServiceImpl teacherService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"TEACHER"} )
    public void join_Application_Should_Redirect_To_Page_With_Default_Message() throws Exception {
        // Arrange
        RequestBuilder request = get("/teacher/headApplication/1");
        ResultMatcher matcher = status().isFound();

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"NONE"} )
    public void join_Application_Should_Be_Forbidden() throws Exception {
        // Arrange
        RequestBuilder request = get("/teacher/headApplication/1");
        ResultMatcher matcher = status().isForbidden();

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"TEACHER"} )
    public void approve_Application_Should_Redirect_To_Page_With_Default_Message() throws Exception {
        // Arrange
        RequestBuilder request = get("/teacher/approveApplication/1");
        ResultMatcher matcher = status().isFound();

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"NONE"} )
    public void approve_Application_Should_Be_Forbidden() throws Exception {
        // Arrange
        RequestBuilder request = get("/teacher/approveApplication/1");
        ResultMatcher matcher = status().isForbidden();

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"TEACHER"} )
    public void disapprove_Application_Should_Redirect_To_Page_With_Default_Message() throws Exception {
        // Arrange
        RequestBuilder request = get("/teacher/disapproveApplication/1");
        ResultMatcher matcher = status().isFound();

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"NONE"} )
    public void disapprove_Application_Should_Be_Forbidden() throws Exception {
        // Arrange
        RequestBuilder request = get("/teacher/disapproveApplication/1");
        ResultMatcher matcher = status().isForbidden();

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }
}
