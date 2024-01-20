package com.graduation.system.data.repository;

import com.graduation.system.data.entity.Role;
import com.graduation.system.data.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(UserRole role);
}
