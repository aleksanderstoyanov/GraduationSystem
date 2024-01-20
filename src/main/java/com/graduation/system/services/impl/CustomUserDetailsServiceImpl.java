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
import com.graduation.system.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.graduation.system.messages.ErrorMessages.UserErrorMessages;


@Service
@AllArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserModelMapper _userMapper;
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(UserErrorMessages.UserNotFound));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                .collect(Collectors.toList());
        return mapRoles;
    }

    @Override
    public UserDTO findByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(UserErrorMessages.UserNotFound));

        UserDTO userDTO = _userMapper.mapToUserDTO(user);

        StudentDTO studentDTO;

        if (user.getStudent() != null){

            studentDTO = (StudentDTO) _userMapper.mapToModel(user.getStudent(), StudentDTO.class);
            userDTO.setStudentDTO(studentDTO);
        }

        TeacherDTO teacherDTO;
        if (user.getTeacher() != null) {
            teacherDTO = (TeacherDTO) _userMapper.mapToModel(user.getTeacher(), TeacherDTO.class);
            userDTO.setTeacherDTO(teacherDTO);
        }

        return userDTO;
    }
}
