package com.graduation.system.controllers;

import com.graduation.system.data.dto.ThesisDTO;
import com.graduation.system.data.enums.UserRole;
import com.graduation.system.mapping.ThesisModelMapper;
import com.graduation.system.models.ThesisCreateViewModel;
import com.graduation.system.models.ThesisEditViewModel;
import com.graduation.system.models.ThesisViewModel;
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
    private ThesisModelMapper _thesisMapper;
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
                .map(thesis -> (ThesisViewModel)_thesisMapper
                        .mapToModel(thesis, ThesisViewModel.class)
                )
                .collect(Collectors.toList());

        model.addAttribute("message", "My Theses");
        model.addAttribute("studentTheses", studentTheses);

        return "/theses/myTheses.html";
    }

    @GetMapping(value = "/theses/facultyTheses")
    public String facultyTheses(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if((!isInRole(authentication, UserRole.TEACHER.name()) &&
                (!isInRole(authentication, UserRole.STUDENT.name()))
        )
        ){
            throw new IllegalArgumentException();
        }

        String currentUserName = authentication.getName();

        List<ThesisViewModel> facultyTheses = _thesisService.getStudentThesesByFaculty(currentUserName)
                .stream()
                .map(thesis -> (ThesisViewModel)_thesisMapper.mapToModel(thesis, ThesisViewModel.class))
                .collect(Collectors.toList());

        model.addAttribute("message", "Faculty Theses");
        model.addAttribute("facultyTheses", facultyTheses);

        return "/theses/facultyTheses.html";
    }

    @GetMapping(value = "/theses/create/{id}")
    public String create(@PathVariable Long id, Model model) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isInRole(authentication, UserRole.STUDENT.name())){
            throw new IllegalAccessException();
        }

        ThesisCreateViewModel viewModel = new ThesisCreateViewModel();
        viewModel.setApplicationId(id);

        model.addAttribute("thesis", viewModel);

        return "/theses/create.html";
    }

    @PostMapping(value = "/theses/create/{id}")
    public String create(@PathVariable Long id,
                         @Valid @ModelAttribute("thesis") ThesisCreateViewModel viewModel,
                         BindingResult bindingResult,
                         Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isInRole(authentication, UserRole.STUDENT.name())){
            throw new IllegalAccessException();
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("thesis", viewModel);
            viewModel.setApplicationId(id);
            return "/theses/create.html";
        }

        _thesisService.createThesis((ThesisDTO) _thesisMapper.mapToModel(viewModel, ThesisDTO.class), id);

        return "redirect:/theses/myTheses";
    }

    @GetMapping(value = "/theses/edit/{id}")
    public String edit(@PathVariable Long id, Model model) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isInRole(authentication, UserRole.STUDENT.name())){
            throw new IllegalAccessException();
        }


        ThesisEditViewModel viewModel =
                Arrays.asList(_thesisService.getStudentThesisById(id))
                        .stream()
                        .map(thesis -> (ThesisEditViewModel) _thesisMapper
                                .mapToModel(thesis, ThesisEditViewModel.class)
                        )
                        .collect(Collectors.toList())
                        .get(0);

        if (viewModel == null){
            throw new IllegalArgumentException();
        }

        model.addAttribute("thesis", viewModel);

        return "/theses/edit.html";
    }


    @PostMapping(value = "/theses/edit/{id}")
    public String edit(@PathVariable Long id,
                       @Valid @ModelAttribute("thesis") ThesisEditViewModel dto,
                       BindingResult bindingResult,
                       Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isInRole(authentication, UserRole.STUDENT.name())) {
            throw new IllegalAccessException();
        }

        if (bindingResult.hasErrors()){
            dto.setApplicationId(id);
            model.addAttribute("thesis", dto);
            return "/theses/edit.html";
        }

        _thesisService.updateThesis((ThesisDTO) _thesisMapper.mapToModel(dto, ThesisDTO.class), id);

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
