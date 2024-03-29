package com.graduation.system.data.repository;

import com.graduation.system.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByEgn(String egn);
    @Query(value = "select * from users where username != 'ADMIN'", nativeQuery = true)
    List<User> findAllUsersWithoutAdmin();
}
