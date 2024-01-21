package com.graduation.system.controllers;

import com.graduation.system.builders.review.ReviewCreateViewModelBuilder;
import com.graduation.system.builders.review.ReviewDTOBuilder;
import com.graduation.system.builders.review.ReviewViewModelBuilder;
import com.graduation.system.builders.user.UserDTOBuilder;
import com.graduation.system.builders.user.UserEditViewModelBuilder;
import com.graduation.system.config.MappingConfig;
import com.graduation.system.config.SecurityConfig;
import com.graduation.system.data.dto.ReviewDTO;
import com.graduation.system.data.dto.UserDTO;
import com.graduation.system.data.enums.UserRole;
import com.graduation.system.mapping.ReviewModelMapper;
import com.graduation.system.mapping.UserModelMapper;
import com.graduation.system.models.ReviewCreateViewModel;
import com.graduation.system.models.ReviewViewModel;
import com.graduation.system.models.UserEditViewModel;
import com.graduation.system.services.impl.AdminServiceImpl;
import com.graduation.system.services.impl.ReviewServiceImpl;
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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewsController.class)
@Import({SecurityConfig.class, MappingConfig.class})
public class ReviewControllerTest {
    @MockBean
    ReviewServiceImpl reviewService;

    @MockBean
    ReviewModelMapper reviewModelMapper;

    @Autowired
    MockMvc mockMvc;
    ReviewDTO dto;
    ReviewViewModel viewModel;

    ReviewCreateViewModel createViewModel;

    @BeforeEach
    public void setup(){
        ReviewDTOBuilder dtoBuilder = new ReviewDTOBuilder();

        dtoBuilder.id(1L);
        dtoBuilder.text("Test");
        dtoBuilder.summary("Summary");
        dtoBuilder.isGranted(true);

        ReviewViewModelBuilder viewModelBuilder = new ReviewViewModelBuilder();
        viewModelBuilder.id(1L);
        viewModelBuilder.text("Test");
        viewModelBuilder.summary("Summary");
        viewModelBuilder.isGranted(true);

        ReviewCreateViewModelBuilder createViewModelBuilder = new ReviewCreateViewModelBuilder();
        createViewModelBuilder.text("Test");
        createViewModelBuilder.summary("Summary");
        createViewModelBuilder.isGranted(true);

        dto = dtoBuilder.getReview();
        viewModel = viewModelBuilder.getReview();
        createViewModel = createViewModelBuilder.getReview();
    }


    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"TEACHER"} )
    public void create_Page_Should_Return_Default_Message() throws Exception {

        // Arrange
        RequestBuilder request = get("/reviews/create/1");
        ResultMatcher matcher = content().string(containsString("Submit Review"));

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"STUDENT"} )
    public void create_Page_Should_Be_Forbidden() throws Exception {

        // Arrange
        RequestBuilder request = get("/reviews/create/1");
        ResultMatcher matcher = status().isForbidden();

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"STUDENT"} )
    public void details_Page_Should_Return_Default_Message() throws Exception {

        // Arrange
        RequestBuilder request = get("/reviews/details/1");
        ResultMatcher matcher = content().string(containsString("Test"));

        when(reviewService.getById(1L)).thenReturn(dto);
        when(reviewModelMapper.mapToModel(dto, ReviewViewModel.class)).thenReturn(viewModel);

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

    @Test
    @WithMockUser(username = "test", password = "123123", authorities = {"STUDENT"} )
    public void details_Page_Should_Be_Forbidden() throws Exception {

        // Arrange
        RequestBuilder request = get("/reviews/details/1");
        ResultMatcher matcher = content().string(containsString("Test"));

        when(reviewService.getById(1L)).thenReturn(dto);
        when(reviewModelMapper.mapToModel(dto, ReviewViewModel.class)).thenReturn(viewModel);

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(matcher);
    }

}
