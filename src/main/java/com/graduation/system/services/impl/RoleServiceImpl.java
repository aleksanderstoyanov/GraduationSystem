package com.graduation.system.services.impl;

import com.graduation.system.entity.Role;
import com.graduation.system.enums.UserRole;
import com.graduation.system.repository.RoleRepository;
import com.graduation.system.services.contracts.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository _repository;

    @Override
    public Role getByRole(String roleName){
        return _repository.findByRole(Enum.valueOf(UserRole.class, roleName));
    }

    @Override
    public void createRole(String roleName){
        if (getByRole(roleName) == null){
            Role role = new Role();
            role.setRole(Enum.valueOf(UserRole.class, roleName));
            _repository.save(role);
        }
    }
}
