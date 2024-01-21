package com.graduation.system.controllers;

import com.graduation.system.builders.application.ApplicationCreateViewModelBuilder;
import com.graduation.system.builders.application.ApplicationDTOBuilder;
import com.graduation.system.builders.application.ApplicationEditViewModelBuilder;
import com.graduation.system.builders.application.ApplicationViewModelBuilder;
import com.graduation.system.config.SecurityConfig;
import com.graduation.system.data.dto.ApplicationDTO;
import com.graduation.system.mapping.ApplicationModelMapper;
import com.graduation.system.models.ApplicationCreateViewModel;
import com.graduation.system.models.ApplicationEditViewModel;
import com.graduation.system.models.ApplicationViewModel;
import com.graduation.system.services.impl.ApplicationServiceImpl;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Getter
@Import({SecurityConfig.class})
@WebMvcTest(ApplicationsController.class)
public class ApplicationControllerTest {

    @MockBean
    ApplicationServiceImpl applicationService;

    @MockBean
    ApplicationModelMapper applicationModelMapper;

    @Autowired
    MockMvc mockMvc;

    ApplicationDTO dto;
    ApplicationViewModel viewModel;
    ApplicationEditViewModel editViewModel;
    ApplicationCreateViewModel createViewModel;


    @BeforeEach
    public void setup(){
        ApplicationDTOBuilder dtoBuilder = new ApplicationDTOBuilder();
        dtoBuilder.id(1L);
        dtoBuilder.task("Test");
        dtoBuilder.subject("Test");
        dtoBuilder.purpose("Test");

        ApplicationViewModelBuilder viewModelBuilder = new ApplicationViewModelBuilder();
        viewModelBuilder.id(1L);
        viewModelBuilder.task("Test");
        viewModelBuilder.subject("Test");
        viewModelBuilder.purpose("Test");

        ApplicationEditViewModelBuilder editViewModelBuilder = new ApplicationEditViewModelBuilder();
        editViewModelBuilder.id(1L);
        editViewModelBuilder.task("Test");
        editViewModelBuilder.subject("Test");
        editViewModelBuilder.purpose("Test");

        ApplicationCreateViewModelBuilder createViewModelBuilder = new ApplicationCreateViewModelBuilder();
        editViewModelBuilder.id(1L);
        editViewModelBuilder.task("Test");
        editViewModelBuilder.subject("Test");
        editViewModelBuilder.purpose("Test");

        dto = dtoBuilder.getApplication();
        viewModel = viewModelBuilder.getApplication();
        editViewModel = editViewModelBuilder.getApplication();
        createViewModel = createViewModelBuilder.getApplication();
    }

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"STUDENT"} )
    public void create_Page_Should_Return_Default_Message() throws Exception {
        // Arrange
        RequestBuilder request = get("/applications/create");
        ResultMatcher matcher = content().string(containsString("Create Application"));

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"NONE"} )
    public void create_Page_Should_Be_Forbidden() throws Exception {
        // Arrange
        RequestBuilder request = get("/applications/create");
        ResultMatcher matcher = status().isForbidden();

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"STUDENT"} )
    public void edit_Page_Should_Return_Default_Message() throws Exception {
        // Arrange
        RequestBuilder request = get("/applications/edit/1");
        ResultMatcher matcher = content().string(containsString("Update Application"));

        when(applicationService.getApplicationById(1L)).thenReturn(dto);
        when(applicationModelMapper.mapToModel(dto, ApplicationEditViewModel.class)).thenReturn(editViewModel);

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"NONE"} )
    public void edit_Page_Should_Be_Forbidden() throws Exception {
        // Arrange
        RequestBuilder request = get("/applications/edit/1");
        ResultMatcher matcher = status().isForbidden();

        when(applicationService.getApplicationById(1L)).thenReturn(dto);
        when(applicationModelMapper.mapToModel(dto, ApplicationEditViewModel.class)).thenReturn(editViewModel);


        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"STUDENT"} )
    public void myApplications_Page_Should_Return_Default_Message() throws Exception{
        // Arrange
        RequestBuilder request = get("/applications/myApplications");
        ResultMatcher matcher = content().string(containsString("My Applications"));

        when(applicationService.getStudentApplications("test")).thenReturn(List.of(dto));
        when(applicationModelMapper.mapToModel(dto, ApplicationViewModel.class)).thenReturn(List.of(viewModel));


        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"NONE"} )
    public void myApplications_Page_Should_Be_Forbidden() throws Exception {
        // Arrange
        RequestBuilder request = get("/applications/myApplications");
        ResultMatcher matcher = status().isForbidden();

        when(applicationService.getStudentApplications("test")).thenReturn(List.of(dto));
        when(applicationModelMapper.mapToModel(dto, ApplicationViewModel.class)).thenReturn(List.of(viewModel));


        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }


    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"STUDENT"} )
    public void facultyApplications_Page_Should_Return_Default_Message() throws Exception{
        // Arrange
        RequestBuilder request = get("/applications/facultyApplications");
        ResultMatcher matcher = content().string(containsString("Student Applications"));

        when(applicationService.getStudentApplicationsByFaculty("test")).thenReturn(List.of(dto));
        when(applicationModelMapper.mapToModel(dto, ApplicationViewModel.class)).thenReturn(viewModel);

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"NONE"} )
    public void facultyApplications_Page_Should_Be_Forbidden() throws Exception {
        // Arrange
        RequestBuilder request = get("/applications/facultyApplications");
        ResultMatcher matcher = status().isForbidden();

        when(applicationService.getStudentApplications("test")).thenReturn(List.of(dto));
        when(applicationModelMapper.mapToModel(dto, ApplicationViewModel.class)).thenReturn(List.of(viewModel));


        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }


    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"STUDENT"} )
    public void delete_Page_Should_Redirect_When_Completed() throws Exception{
        // Arrange
        RequestBuilder request = get("/applications/delete/1");
        ResultMatcher matcher = status().isFound();

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }


    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"NONE"} )
    public void delete_Page_Should_Be_Forbidden() throws Exception{
        // Arrange
        RequestBuilder request = get("/applications/delete/1");
        ResultMatcher matcher = status().isForbidden();

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

}
