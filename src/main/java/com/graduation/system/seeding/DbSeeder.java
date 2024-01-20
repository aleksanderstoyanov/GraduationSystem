package com.graduation.system.seeding;


import com.graduation.system.data.dto.RegisterDTO;
import com.graduation.system.data.entity.Role;
import com.graduation.system.data.enums.FacultyType;
import com.graduation.system.data.enums.UserRole;
import com.graduation.system.services.impl.FacultyServiceImpl;
import com.graduation.system.services.impl.RoleServiceImpl;
import com.graduation.system.services.impl.RegisterServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class DbSeeder implements CommandLineRunner {
    @Autowired
    private RoleServiceImpl _roleService;
    @Autowired
    private RegisterServiceImpl _userService;
    @Autowired
    private FacultyServiceImpl _facultyService;

    @Override
    public void run(String... args) throws Exception {
        seedRoles();
        seedAdminUser();
        seedFaculties();
        seedStudentUser();
        seedTeacherUser();
    }

    public void seedAdminUser() throws Exception{
        RegisterDTO registerDto = new RegisterDTO();

        registerDto.setEmail("admin@admin.com");
        registerDto.setFirstName("Admin");
        registerDto.setLastName("Admin");
        registerDto.setUsername("admin");
        registerDto.setPassword("123123");

        if (_userService.findByUsername(registerDto.getUsername()) == null){
            Role adminRole = _roleService.getByRole(UserRole.ADMIN.name());
            _userService.registerWithRole(registerDto, adminRole);
        }
    }

    public void seedStudentUser() throws Exception{
        RegisterDTO registerDto = new RegisterDTO();

        registerDto.setEmail("student@student.com");
        registerDto.setFirstName("Student");
        registerDto.setLastName("Student");
        registerDto.setUsername("student");
        registerDto.setEgn("0141246326");
        registerDto.setFaculty("INFORMATICS");
        registerDto.setPassword("123123");

        if (_userService.findByUsername(registerDto.getUsername()) == null){
            _userService.register(registerDto);
        }
    }

    public void seedTeacherUser() throws Exception{
        RegisterDTO registerDto = new RegisterDTO();

        registerDto.setEmail("teacher@teacher.com");
        registerDto.setFirstName("Teacher");
        registerDto.setLastName("Teacher");
        registerDto.setUsername("teacher");
        registerDto.setEgn("0141246327");
        registerDto.setFaculty("INFORMATICS");
        registerDto.setPassword("123123");

        if (_userService.findByUsername(registerDto.getUsername()) == null){
            _userService.register(registerDto);
        }
    }

    public void seedRoles(){

        Arrays.stream(UserRole.values()).forEach(field -> {
            if (_roleService.getByRole(field.name()) == null){
                _roleService.createRole(field.name());
            }
        });
    }

    public void seedFaculties(){
        Arrays.stream(FacultyType.values()).forEach(field -> {
            if (_facultyService.getByName(field.name()) == null){
                _facultyService.create(field.name());
            }
        });
    }

}
