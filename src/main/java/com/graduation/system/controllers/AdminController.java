package com.graduation.system.controllers;

import com.graduation.system.dto.AdminEditDTO;
import com.graduation.system.entity.User;
import com.graduation.system.enums.FacultyType;
import com.graduation.system.enums.Position;
import com.graduation.system.enums.UserRole;
import com.graduation.system.model.UserViewModel;
import com.graduation.system.services.impl.AdminServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AdminController {

    @Autowired
    private AdminServiceImpl _adminService;

    @GetMapping(value = "/admin/users")
    public String users(Model model){
        List<UserViewModel> users = _adminService.findAllUsers()
                .stream().map(user -> new UserViewModel(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getFaculty(),
                        user.getRoles().get(0).getRole().name()
                )).collect(Collectors.toList());


        model.addAttribute("message", "All Users");
        model.addAttribute("users", users);

        return "/admin/users.html";
    }

    @GetMapping(value = "/admin/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        AdminEditDTO editDTO = Arrays.asList(_adminService.findById(id))
                .stream().map(user -> new AdminEditDTO(
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEgn(),
                        null,
                        user.getFaculty(),
                        user.getRoles().get(0).getRole().name()
                )).collect(Collectors.toList()).get(0);

        List<FacultyType> faculties = Arrays.stream(FacultyType.values()).toList();
        model.addAttribute("faculties", faculties);


        List<UserRole> roles = Arrays.stream(UserRole.values()).toList();
        model.addAttribute("roles", roles);

        List<Position> positions = Arrays.stream(Position.values()).toList();
        model.addAttribute("positions", positions);

        model.addAttribute("user", editDTO);

        return "admin/edit.html";
    }

    @PostMapping(value = "/admin/edit/{id}")
    public String edit(@PathVariable Long id,
                       @Valid @ModelAttribute AdminEditDTO editDTO,
                       BindingResult bindingResult,
                       Model model) throws Exception{
        if (bindingResult.hasErrors()){

            List<FacultyType> faculties = Arrays.stream(FacultyType.values()).toList();
            model.addAttribute("faculties", faculties);


            List<UserRole> roles = Arrays.stream(UserRole.values()).toList();
            model.addAttribute("roles", roles);

            List<Position> positions = Arrays.stream(Position.values()).toList();
            model.addAttribute("positions", positions);

            return "admin/edit.html";
        }

        _adminService.updateUser(editDTO);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/admin/delete/{id}")
    public String delete(@PathVariable Long id) throws Exception{
        _adminService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
