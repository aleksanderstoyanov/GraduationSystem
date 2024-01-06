package com.graduation.system.services.contracts;

import com.graduation.system.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByEmail(String email);
}
