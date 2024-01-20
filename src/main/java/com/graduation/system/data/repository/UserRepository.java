package com.graduation.system.data.repository;

import com.graduation.system.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByEgn(String egn);
    @Query(value = "select * from users where username != 'ADMIN'", nativeQuery = true)
    List<User> findAllUsersWithoutAdmin();
}
