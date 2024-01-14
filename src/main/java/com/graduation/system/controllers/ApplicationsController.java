package com.graduation.system.controllers;

import com.graduation.system.dto.ApplicationDTO;
import com.graduation.system.enums.UserRole;
import com.graduation.system.mapping.ApplicationModelMapper;
import com.graduation.system.services.impl.ApplicationServiceImpl;
import com.graduation.system.viewmodels.ApplicationCreateViewModel;
import com.graduation.system.viewmodels.ApplicationEditViewModel;
import com.graduation.system.viewmodels.ApplicationViewModel;
import com.graduation.system.viewmodels.UserViewModel;
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
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ApplicationsController {

    @Autowired
    private ApplicationModelMapper _applicationMapper;
    @Autowired
    private ApplicationServiceImpl _applicationService;

    @GetMapping(value = "/applications/create")
    public String create(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!isInRole(authentication, UserRole.STUDENT.name())) {
            throw new IllegalArgumentException();
        }

        ApplicationCreateViewModel viewModel = new ApplicationCreateViewModel();

        model.addAttribute("application", viewModel);

        return "/applications/create.html";
    }

    @GetMapping(value = "/applications/myApplications")
    public String myApplications(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!isInRole(authentication, UserRole.STUDENT.name())) {
            throw new IllegalArgumentException();
        }

        String currentUserName = authentication.getName();

        List<ApplicationViewModel> myApplications = _applicationService
                .getStudentApplications(currentUserName)
                .stream()
                .map(application -> (ApplicationViewModel)_applicationMapper
                        .mapToModel(application, ApplicationViewModel.class)
                )
                .collect(Collectors.toList());

        model.addAttribute("message", "My Applications");
        model.addAttribute("applications", myApplications);

        return "/applications/myApplications.html";
    }

    @GetMapping(value = "/applications/facultyApplications")
    public String facultyApplications(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if((!isInRole(authentication, UserRole.TEACHER.name()) &&
           (!isInRole(authentication, UserRole.STUDENT.name()))
        )
        ){
            throw new IllegalArgumentException();
        }

        String currentUserName = authentication.getName();

        List<ApplicationViewModel> viewModels = _applicationService
                .getStudentApplicationsByFaculty(currentUserName)
                .stream()
                .map(application -> (ApplicationViewModel) _applicationMapper
                        .mapToModel(application, ApplicationViewModel.class)
                )
                .collect(Collectors.toList());

        model.addAttribute("message", "Student Applications");
        model.addAttribute("facultyApplications", viewModels);

        return "/applications/facultyApplications.html";
    }

    @PostMapping(value = "/applications/create")
    public String create(@Valid @ModelAttribute("application") ApplicationDTO viewModel,
                         BindingResult bindingResult,
                         Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!isInRole(authentication, UserRole.STUDENT.name())) {
            throw new IllegalArgumentException();
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("application", viewModel);
            return "/applications/create.html";
        }

        String currentUserName = authentication.getName();
        _applicationService.createStudentApplication(viewModel, currentUserName);

        return "redirect:/applications/myApplications";
    }

    @GetMapping(value = "/applications/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!isInRole(authentication, UserRole.STUDENT.name())) {
            throw new IllegalArgumentException();
        }

        ApplicationEditViewModel applicationDTO = Arrays.asList(_applicationService.getApplicationById(id))
                .stream()
                .map(application -> (ApplicationEditViewModel)_applicationMapper.mapToModel(application, ApplicationEditViewModel.class))
                .toList()
                .get(0);

        if (applicationDTO == null){
            throw new IllegalArgumentException();
        }

        model.addAttribute("applicationRecord", applicationDTO);

        return "/applications/edit.html";
    }

    @PostMapping(value = "/applications/edit/{id}")
    public String edit(@PathVariable Long id,
                       @Valid @ModelAttribute("applicationRecord") ApplicationEditViewModel editDTO,
                       BindingResult bindingResult,
                       Model model) throws Exception
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!isInRole(authentication, UserRole.STUDENT.name())) {
            throw new IllegalArgumentException();
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("applicationRecord", editDTO);
            return "/applications/edit.html";
        }

       _applicationService.updateStudentApplication((ApplicationDTO) _applicationMapper
                       .mapToModel(editDTO, ApplicationDTO.class), id);

        return "redirect:/applications/myApplications";
    }

    @GetMapping(value = "/applications/delete/{id}")
    public String delete(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!isInRole(authentication, UserRole.STUDENT.name())) {
            throw new IllegalArgumentException();
        }

        _applicationService.deleteStudentApplication(id);

        return "redirect:/applications/myApplications";
    }
    private boolean isInRole(Authentication authentication, String role) {
        boolean hasAuthority = authentication
                .getAuthorities()
                .stream()
                .filter(authority -> authority.getAuthority() == role)
                .findAny().isPresent();

        return hasAuthority;
    }
}
