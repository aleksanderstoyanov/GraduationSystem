package com.graduation.system.services.contracts;


import com.graduation.system.dto.AdminEditDTO;
import com.graduation.system.entity.User;

import java.util.List;

public interface AdminService {
    void deleteUser(Long id) throws Exception;
    void updateUser(AdminEditDTO editDTO) throws Exception;
    User findById(Long id);
    List<User> findAllUsers();
}
