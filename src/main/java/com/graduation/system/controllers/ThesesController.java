package com.graduation.system.controllers;

import com.graduation.system.dto.ThesisCreateDTO;
import com.graduation.system.dto.ThesisEditDTO;
import com.graduation.system.enums.UserRole;
import com.graduation.system.model.ThesisViewModel;
import com.graduation.system.services.impl.ThesisServiceImpl;
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
public class ThesesController {

    @Autowired
    private ThesisServiceImpl _thesisService;

    @GetMapping(value = "/theses/myTheses")
    public String myTheses (Model model) throws IllegalAccessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isInRole(authentication, UserRole.STUDENT.name())){
            throw new IllegalAccessException();
        }

        String currentUsername = authentication.getName();

        List<ThesisViewModel> studentTheses = _thesisService
                .getStudentTheses(currentUsername)
                .stream()
                .map(thesis -> new ThesisViewModel(
                        thesis.getId(),
                        thesis.getTitle(),
                        thesis.getText()
                ))
                .collect(Collectors.toList());

        model.addAttribute("message", "My Theses");
        model.addAttribute("studentTheses", studentTheses);

        return "/theses/myTheses.html";
    }

    @GetMapping(value = "/theses/create/{id}")
    public String create(@PathVariable Long id, Model model) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isInRole(authentication, UserRole.STUDENT.name())){
            throw new IllegalAccessException();
        }


        ThesisCreateDTO createDTO = new ThesisCreateDTO();
        createDTO.setApplicationId(id);
        model.addAttribute("thesis", createDTO);

        return "/theses/create.html";
    }

    @PostMapping(value = "/theses/create/{id}")
    public String create(@PathVariable Long id,
                         @Valid @ModelAttribute ThesisCreateDTO createDTO,
                         BindingResult bindingResult,
                         Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isInRole(authentication, UserRole.STUDENT.name())){
            throw new IllegalAccessException();
        }

        _thesisService.createThesis(createDTO, id);

        return "redirect:/theses/myTheses";
    }

    @GetMapping(value = "/theses/edit/{id}")
    public String edit(@PathVariable Long id, Model model) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isInRole(authentication, UserRole.STUDENT.name())){
            throw new IllegalAccessException();
        }


        ThesisEditDTO editDTO =
                Arrays.asList(_thesisService.getStudentThesisById(id))
                        .stream()
                        .map(thesis -> new ThesisEditDTO(
                                thesis.getId(),
                                thesis.getTitle(),
                                thesis.getText()
                        ))
                        .collect(Collectors.toList())
                        .get(0);

        if (editDTO == null){
            throw new IllegalArgumentException();
        }

        model.addAttribute("thesis", editDTO);

        return "/theses/edit.html";
    }


    @PostMapping(value = "/theses/edit/{id}")
    public String edit(@PathVariable Long id,
                       @Valid @ModelAttribute ThesisEditDTO editDTO,
                       BindingResult bindingResult,
                       Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isInRole(authentication, UserRole.STUDENT.name())) {
            throw new IllegalAccessException();
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("thesis", editDTO);
            return "/theses/edit/"+id;
        }

        _thesisService.updateThesis(editDTO, id);

        return "redirect:/theses/myTheses";
    }

    @GetMapping(value = "/theses/delete/{id}")
    public String delete(@PathVariable Long id){
        _thesisService.deleteThesis(id);

        return "redirect:/theses/myTheses";
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
