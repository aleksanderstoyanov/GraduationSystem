package com.graduation.system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String index(Model model){

        model.addAttribute("message", "Welcome, before proceeding, please sign-in or register");

        return "index.html";
    }
}
