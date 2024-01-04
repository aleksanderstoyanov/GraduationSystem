package com.graduation.system.repository;

import com.graduation.system.entity.Role;
import com.graduation.system.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(UserRole role);
}
