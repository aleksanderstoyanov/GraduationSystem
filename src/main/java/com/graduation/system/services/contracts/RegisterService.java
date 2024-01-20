package com.graduation.system.services.contracts;

import com.graduation.system.data.dto.RegisterDTO;
import com.graduation.system.data.entity.Role;
import com.graduation.system.data.entity.User;

public interface RegisterService {
    User findByEgn(String egn);
    User findByUsername(String username);
    void register(RegisterDTO registerDto) throws Exception;
    void registerWithRole(RegisterDTO registerDto, Role role) throws Exception;
}
