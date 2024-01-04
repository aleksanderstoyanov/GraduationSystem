package com.graduation.system.services.contracts;

import com.graduation.system.entity.Role;

public interface RoleService {

    Role getByRole(String roleName);

    void createRole(String roleName);
}
