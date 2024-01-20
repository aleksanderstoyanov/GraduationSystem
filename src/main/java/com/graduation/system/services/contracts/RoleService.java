package com.graduation.system.services.contracts;

import com.graduation.system.data.entity.Role;

public interface RoleService {

    Role getByRole(String roleName);

    void createRole(String roleName);
}
