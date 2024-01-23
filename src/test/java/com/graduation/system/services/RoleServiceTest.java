package com.graduation.system.services;

import com.graduation.system.builders.role.RoleBuilder;
import com.graduation.system.data.entity.Faculty;
import com.graduation.system.data.entity.Role;
import com.graduation.system.data.enums.UserRole;
import com.graduation.system.data.repository.RoleRepository;
import com.graduation.system.services.contracts.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RoleServiceTest {
    @MockBean
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    Role role;

    @BeforeEach()
    public void setup(){
        RoleBuilder builder = new RoleBuilder();

        builder.name(UserRole.TEST);

        role = builder.getRole();
    }

    @Test
    public void get_By_Name_Should_Return_Record(){
        when(roleRepository.findByRole(UserRole.TEST)).thenReturn(role);

        Role foundRole = roleService.getByRole("TEST");

        assertThat(foundRole.getRole()).isEqualTo(UserRole.TEST);
    }
}
