package com.graduation.system.repository;


import com.graduation.system.builders.user.UserBuilder;
import com.graduation.system.data.entity.User;
import com.graduation.system.data.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.function.Predicate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    User user;

    @BeforeEach
    public void setup(){
        UserBuilder builder = new UserBuilder();

        builder.username("test");
        builder.firstName("Test");
        builder.lastName("Test");
        builder.password("123123");
        builder.email("test@test.com");
        builder.egn("0141246325");

        user = builder.getUser();
        testEntityManager.persistAndFlush(user);
    }

    @Test
    public void find_All_Users_Without_Admin_Should_Return_Records(){
        Predicate<User> predicate = (user) -> user.getEmail() == "test@test.com";
        assertThat(userRepository.findAllUsersWithoutAdmin().stream().filter(predicate).count()).isEqualTo(1);
    }

    @Test
    public void find_User_By_Email_Should_Return_Record(){
        assertThat(userRepository.findByEmail("test@test.com").stream().count()).isEqualTo(1);
    }

    @Test
    public void find_User_By_Username_Should_Return_Record(){
        assertThat(userRepository.findByUsername("test").stream().count()).isEqualTo(1);
    }

    @Test
    public void find_User_By_Egn_Should_Return_Record(){
        assertThat(userRepository.findByEgn("0141246325").stream().count()).isEqualTo(1);
    }

}
