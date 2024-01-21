package com.graduation.system.controllers;

import com.graduation.system.data.dto.ReviewDTO;
import com.graduation.system.data.enums.UserRole;
import com.graduation.system.mapping.ReviewModelMapper;
import com.graduation.system.models.ReviewCreateViewModel;
import com.graduation.system.models.ReviewViewModel;
import com.graduation.system.services.impl.ReviewServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ReviewsController {

    @Autowired
    private ReviewModelMapper _reviewMapper;
    @Autowired
    private ReviewServiceImpl _reviewService;

    @GetMapping( value = "/reviews/details/{id}")
    public String details(@PathVariable Long id, Model model) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isInRole(authentication, UserRole.STUDENT.name())) {
            throw new IllegalAccessException();
        }

        ReviewViewModel reviewModel =
                Arrays.asList(_reviewService.getById(id))
                        .stream()
                        .map(review -> (ReviewViewModel) _reviewMapper.mapToModel(review, ReviewViewModel.class))
                        .collect(Collectors.toList())
                        .get(0);

        if (reviewModel == null){
            throw new IllegalArgumentException();
        }

        model.addAttribute("review", reviewModel);

        return "/reviews/details.html";
    }

    @GetMapping(value = "/reviews/create/{id}")
    public String create(@PathVariable Long id, Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isInRole(authentication, UserRole.TEACHER.name())) {
            throw new IllegalAccessException();
        }

        ReviewCreateViewModel viewModel = new ReviewCreateViewModel();
        viewModel.setThesisId(id);

        model.addAttribute("review", viewModel);

        return "/reviews/create.html";
    }

    @PostMapping(value = "/reviews/create/{id}")
    public String create(@PathVariable Long id,
                         @Valid @ModelAttribute("review") ReviewCreateViewModel viewModel,
                         BindingResult bindingResult,
                         Model model) throws IllegalAccessException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isInRole(authentication, UserRole.TEACHER.name())) {
            throw new IllegalAccessException();
        }


        if (bindingResult.hasErrors()){
            viewModel.setThesisId(id);
            model.addAttribute("review", viewModel);
            return "/reviews/create.html";
        }

        _reviewService.createReview((ReviewDTO) _reviewMapper.mapToModel(viewModel, ReviewDTO.class), id);

        return "redirect:/theses/facultyTheses";
    }


    private boolean isInRole(Authentication authentication, String role) {
        boolean hasAuthority = authentication
                .getAuthorities()
                .stream()
                .filter(authority -> authority.getAuthority().equals(role))
                .findAny().isPresent();

        return hasAuthority;
    }

}
