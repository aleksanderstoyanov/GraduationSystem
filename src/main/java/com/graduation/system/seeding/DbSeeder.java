package com.graduation.system.seeding;


import com.graduation.system.dto.RegisterDTO;
import com.graduation.system.entity.Role;
import com.graduation.system.enums.FacultyType;
import com.graduation.system.enums.UserRole;
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
            _userService.registerAdmin(registerDto, adminRole);
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
