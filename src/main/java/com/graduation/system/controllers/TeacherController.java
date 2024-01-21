package com.graduation.system.controllers;

import com.graduation.system.data.enums.UserRole;
import com.graduation.system.services.impl.ApplicationServiceImpl;
import com.graduation.system.services.impl.TeacherServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class TeacherController {
    @Autowired
    private ApplicationServiceImpl _applicationService;

    @Autowired
    private TeacherServiceImpl _teacherService;

    @GetMapping(value = "/teacher/headApplication/{id}")
    public String joinApplication(@PathVariable Long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!isInRole(authentication, UserRole.TEACHER.name()))
        {
            throw new IllegalArgumentException();
        }

        String currentUsername = authentication.getName();
        _teacherService.headApplication(id, currentUsername);

        return "redirect:/applications/facultyApplications";
    }

    @GetMapping(value = "/teacher/approveApplication/{id}")
    public String approveApplication(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!isInRole(authentication, UserRole.TEACHER.name()))
        {
            throw new IllegalArgumentException();
        }

        String currentUsername = authentication.getName();
        _teacherService.approveApplication(id, currentUsername);

        return "redirect:/applications/facultyApplications";
    }

    @GetMapping(value = "/teacher/disapproveApplication/{id}")
    public String disapproveApplication(@PathVariable Long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!isInRole(authentication, UserRole.TEACHER.name()))
        {
            throw new IllegalArgumentException();
        }

        String currentUsername = authentication.getName();
        _teacherService.disapproveApplication(id, currentUsername);

        return "redirect:/applications/facultyApplications";
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
