package com.graduation.system.services.impl;


import com.graduation.system.data.dto.RegisterDTO;
import com.graduation.system.data.entity.Faculty;
import com.graduation.system.data.entity.Role;
import com.graduation.system.data.entity.User;
import com.graduation.system.data.repository.UserRepository;
import com.graduation.system.exceptions.UserAlreadyExistsException;
import com.graduation.system.services.contracts.RegisterService;
import com.graduation.system.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.graduation.system.messages.ErrorMessages.UserErrorMessages;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserRepository _repository;

    @Autowired
    private FacultyServiceImpl _facultyService;

    @Autowired
    private RoleServiceImpl _roleService;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    @Override
    public User findByEgn(String egn){
        return _repository.findByEgn(egn)
                .orElseThrow(() -> new UserNotFoundException(UserErrorMessages.UserNotFound));
    }

    @Override
    public User findByUsername(String username){
        return _repository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(UserErrorMessages.UserNotFound));
    }

    @Override
    public void register(RegisterDTO registerDto) throws Exception{
        if (_repository.findByUsername(registerDto.getUsername()) != null){
            return;
        }
        else{
            User user = new User();

            Role role = _roleService.getByRole("NONE");

            user.setEmail(registerDto.getEmail());
            user.setFirstName(registerDto.getFirstName());
            user.setLastName(registerDto.getLastName());
            user.setEgn(registerDto.getEgn());
            user.setUsername(registerDto.getUsername());
            user.setPassword(_passwordEncoder.encode(registerDto.getPassword()));
            user.setRoles(Arrays.asList(role));

            Faculty faculty = _facultyService.getByName(registerDto.getFaculty());

            if(faculty != null){

                user.setFaculty(faculty);
            }
            _repository.save(user);
        }
    }

    @Override
    public void registerWithRole(RegisterDTO registerDto, Role role) throws Exception{
        if (_repository.findByUsername(registerDto.getUsername()) != null){
            throw new UserAlreadyExistsException(UserErrorMessages.UserAlreadyExists);
        }
        else{
            User user = new User();

            user.setEmail(registerDto.getEmail());
            user.setFirstName(registerDto.getFirstName());
            user.setLastName(registerDto.getLastName());
            user.setUsername(registerDto.getUsername());

            user.setRoles(Arrays.asList(role));
            user.setPassword(_passwordEncoder.encode(registerDto.getPassword()));

            Faculty faculty = _facultyService.getByName(registerDto.getFaculty());

            if(faculty != null){

                user.setFaculty(faculty);
            }
            _repository.save(user);
        }
    }
}
