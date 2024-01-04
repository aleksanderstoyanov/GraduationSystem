package com.graduation.system.controllers;

import com.graduation.system.dto.RegisterDTO;
import com.graduation.system.enums.FacultyType;
import com.graduation.system.services.impl.RegisterServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private RegisterServiceImpl _userService;

    @GetMapping(value = "/register")
    public String register(Model model){
        RegisterDTO registerDto = new RegisterDTO();
        List<FacultyType> faculties = Arrays.stream(FacultyType.values()).toList();

        model.addAttribute("user", registerDto);
        model.addAttribute("faculties", faculties);

        return "auth/register.html";
    }

//    @GetMapping(value = "/login")
//    public String loging(Model model){
//        UserDto
//    }

    @PostMapping(value = "/register")
    public String register(@Valid @ModelAttribute("user") RegisterDTO registerDto,
                           BindingResult bindingResult,
                           Model model) throws Exception {

        if (bindingResult.hasErrors()){
            model.addAttribute("user", registerDto);

            List<FacultyType> faculties = Arrays.stream(FacultyType.values()).toList();
            model.addAttribute("faculties", faculties);

            return "auth/register.html";
        }

        _userService.register(registerDto);

        return "redirect: /login";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "auth/login.html";
    }
}
