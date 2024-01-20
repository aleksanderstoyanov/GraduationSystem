package com.graduation.system.services.contracts;


import com.graduation.system.data.dto.UserDTO;

import java.util.List;

public interface AdminService {
    void deleteUser(Long id) throws Exception;
    void updateUser(UserDTO editDTO) throws Exception;
    UserDTO findById(Long id);
    List<UserDTO> findAllUsers();
}
