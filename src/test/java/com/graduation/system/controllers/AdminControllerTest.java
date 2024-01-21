package com.graduation.system.controllers;

import com.graduation.system.builders.UserDTOBuilder;
import com.graduation.system.builders.UserEditViewModelBuilder;
import com.graduation.system.config.MappingConfig;
import com.graduation.system.config.SecurityConfig;
import com.graduation.system.data.dto.UserDTO;
import com.graduation.system.data.enums.UserRole;
import com.graduation.system.mapping.UserModelMapper;
import com.graduation.system.models.UserEditViewModel;
import com.graduation.system.services.impl.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
@Import({SecurityConfig.class, MappingConfig.class})
public class AdminControllerTest {

    @MockBean
    AdminServiceImpl adminService;

    @MockBean
    UserModelMapper userModelMapper;

    @Autowired
    MockMvc mockMvc;

    UserDTO dto;
    UserEditViewModel viewModel;

    @BeforeEach
    public void setup(){
        UserDTOBuilder dtoBuilder = new UserDTOBuilder();

        dtoBuilder.id(1L);
        dtoBuilder.firstName("Test");
        dtoBuilder.lastName("Test");
        dtoBuilder.email("email@test.com");
        dtoBuilder.egn("0141246325");
        dtoBuilder.faculty("Informatics");
        dtoBuilder.roles(List.of(UserRole.NONE));

        UserEditViewModelBuilder viewModelBuilder = new UserEditViewModelBuilder();
        viewModelBuilder.firstName("Test");
        viewModelBuilder.lastName("Test");
        viewModelBuilder.email("email@test.com");
        viewModelBuilder.egn("0141246325");
        viewModelBuilder.faculty("Informatics");
        viewModelBuilder.roles(List.of(UserRole.NONE));

        dto = dtoBuilder.getUser();
        viewModel = viewModelBuilder.getUser();
    }
    @Test
    @Secured("ADMIN")
    @WithMockUser(username = "admin", password = "123123", authorities = {"ADMIN"} )
    public void admin_All_Users_Page_Should_Return_Default_Message() throws Exception {
        // Arrange
        RequestBuilder request = get("/admin/users");
        ResultMatcher matcher = content().string(containsString("All Users"));

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @Secured("ADMIN")
    @WithMockUser(username = "test", password = "123123", authorities = {"NONE"} )
    public void admin_All_Users_Page_Is_Forbidden() throws Exception {
        // Arrange
        RequestBuilder request = get("/admin/users");
        ResultMatcher matcher = status().isForbidden();

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }


    @Test
    @Secured("ADMIN")
    @WithMockUser(username = "admin", password = "123123", authorities = {"ADMIN"} )
    public void edit_User_Should_Return_Default_Message() throws Exception {
        // Arrange
        when(adminService.findById(1L)).thenReturn(dto);
        when(userModelMapper.mapToUserEditViewModel(dto)).thenReturn(viewModel);

        RequestBuilder request = get("/admin/edit/1");
        ResultMatcher matcher = content().string(containsString("Update User"));

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }
}
