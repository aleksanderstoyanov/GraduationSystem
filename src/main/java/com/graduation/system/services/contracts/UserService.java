package com.graduation.system.services.contracts;

import com.graduation.system.data.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public UserDTO findByEmail(String email);
}
