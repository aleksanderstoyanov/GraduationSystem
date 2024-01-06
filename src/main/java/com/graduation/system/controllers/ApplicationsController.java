package com.graduation.system.controllers;

import com.graduation.system.dto.ApplicationCreateDTO;
import com.graduation.system.dto.ApplicationEditDTO;
import com.graduation.system.entity.Application;
import com.graduation.system.enums.UserRole;
import com.graduation.system.services.impl.ApplicationServiceImpl;
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
    private ApplicationServiceImpl _applicationService;

    @GetMapping(value = "/applications/create")
    public String create(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!isInRole(authentication, UserRole.STUDENT.name())) {
            throw new IllegalArgumentException();
        }

        ApplicationCreateDTO createDTO = new ApplicationCreateDTO();

        model.addAttribute("application", createDTO);

        return "/applications/create.html";
    }

    @GetMapping(value = "/applications/myApplications")
    public String myApplications(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!isInRole(authentication, UserRole.STUDENT.name())) {
            throw new IllegalArgumentException();
        }

        String currentUserName = authentication.getName();
        List<Application> myApplications = _applicationService.getStudentApplications(currentUserName);

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

        List<Application> facultyApplications = _applicationService.getStudentApplicationsByFaculty(currentUserName);

        model.addAttribute("message", "Student Applications");
        model.addAttribute("facultyApplications", facultyApplications);

        return "/applications/facultyApplications.html";
    }

    @PostMapping(value = "/applications/create")
    public String create(@Valid @ModelAttribute("application") ApplicationCreateDTO createDTO,
                         BindingResult bindingResult,
                         Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!isInRole(authentication, UserRole.STUDENT.name())) {
            throw new IllegalArgumentException();
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("application", createDTO);
            return "/applications/create.html";
        }

        String currentUserName = authentication.getName();
        _applicationService.createStudentApplication(createDTO, currentUserName);

        return "redirect:/applications/myApplications";
    }

    @GetMapping(value = "/applications/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!isInRole(authentication, UserRole.STUDENT.name())) {
            throw new IllegalArgumentException();
        }

        ApplicationEditDTO applicationDTO = Arrays.asList(_applicationService.getApplicationById(id))
                .stream().map(application -> new ApplicationEditDTO(
                        application.getId(),
                        application.getSubject(),
                        application.getTask(),
                        application.getPurpose())
                ).collect(Collectors.toList())
                .get(0);

        if (applicationDTO == null){
            throw new IllegalArgumentException();
        }

        model.addAttribute("applicationRecord", applicationDTO);

        return "/applications/edit.html";
    }

    @PostMapping(value = "/applications/edit/{id}")
    public String edit(@PathVariable Long id,
                       @Valid @ModelAttribute("applicationRecord") ApplicationEditDTO editDTO,
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

       _applicationService.updateStudentApplication(editDTO, id);

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
