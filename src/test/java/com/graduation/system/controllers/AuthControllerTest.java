package com.graduation.system.controllers;

import com.graduation.system.config.MappingConfig;
import com.graduation.system.config.SecurityConfig;
import com.graduation.system.services.impl.CustomUserDetailsServiceImpl;
import com.graduation.system.services.impl.RegisterServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({MappingConfig.class})
public class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomUserDetailsServiceImpl userDetailsService;

    @MockBean
    RegisterServiceImpl registerService;

    @Test
    public void login_Page_Should_Return_Default_Message() throws Exception {
        // Arrange
        RequestBuilder request = get("/login");
        ResultMatcher matcher = content().string(containsString("Login"));

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    public void register_Page_Should_Return_Default_Message() throws Exception {
        // Arrange
        RequestBuilder request = get("/register");
        ResultMatcher matcher = content().string(containsString("Registration"));

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

}
