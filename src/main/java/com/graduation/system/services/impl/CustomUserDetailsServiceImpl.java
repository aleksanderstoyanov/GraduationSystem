package com.graduation.system.services.impl;

import com.graduation.system.data.dto.StudentDTO;
import com.graduation.system.data.dto.TeacherDTO;
import com.graduation.system.data.dto.UserDTO;
import com.graduation.system.data.entity.Role;
import com.graduation.system.data.entity.User;
import com.graduation.system.mapping.UserModelMapper;
import com.graduation.system.data.repository.UserRepository;
import com.graduation.system.services.contracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserModelMapper _userMapper;
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                .collect(Collectors.toList());
        return mapRoles;
    }

    @Override
    public UserDTO findByEmail(String email) {

        UserDTO userDTO = _userMapper.mapToUserDTO(userRepository.findByEmail(email));

        StudentDTO studentDTO;
        if (userRepository.findByEmail(email).getStudent() != null){

            studentDTO = (StudentDTO) _userMapper.mapToModel(userRepository.findByEmail(email).getStudent(), StudentDTO.class);
            userDTO.setStudentDTO(studentDTO);
        }

        TeacherDTO teacherDTO;
        if (userRepository.findByEmail(email).getTeacher() != null) {
            teacherDTO = (TeacherDTO) _userMapper.mapToModel(userRepository.findByEmail(email).getTeacher(), TeacherDTO.class);
            userDTO.setTeacherDTO(teacherDTO);
        }

        return userDTO;
    }
}
