package com.graduation.system.repository;

import com.graduation.system.builders.role.RoleBuilder;
import com.graduation.system.data.entity.Role;
import com.graduation.system.data.entity.User;
import com.graduation.system.data.enums.UserRole;
import com.graduation.system.data.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    RoleRepository roleRepository;

    Role role;

    @BeforeEach
    public void setup(){
        RoleBuilder builder = new RoleBuilder();
        builder.name(UserRole.TEST);

        role = builder.getRole();

        testEntityManager.persistAndFlush(role);
    }

    @Test
    public void find_By_Role_Should_Return_Record(){
        assertThat(roleRepository.findByRole(UserRole.TEST).getRole().name()).isEqualTo("TEST");
    }
}
