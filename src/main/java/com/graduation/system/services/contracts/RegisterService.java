package com.graduation.system.services.contracts;

import com.graduation.system.dto.RegisterDTO;
import com.graduation.system.entity.Role;
import com.graduation.system.entity.User;

public interface RegisterService {
    User findByEgn(String egn);
    User findByUsername(String username);
    void register(RegisterDTO registerDto) throws Exception;
    void registerAdmin(RegisterDTO registerDto, Role role) throws Exception;
}
