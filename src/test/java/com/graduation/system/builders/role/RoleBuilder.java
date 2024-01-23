package com.graduation.system.builders.role;

import com.graduation.system.data.entity.Role;
import com.graduation.system.data.enums.UserRole;

public class RoleBuilder {
    private Role role;

    public RoleBuilder(){
        this.role = new Role();
    }

    public RoleBuilder name(UserRole role){
        this.role.setRole(role);
        return this;
    }

    public void resetFaculty(){
        this.role = new Role();
    }

    public Role getRole(){
        return this.role;
    }
}
