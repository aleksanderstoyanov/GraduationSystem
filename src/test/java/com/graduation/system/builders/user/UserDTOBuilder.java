package com.graduation.system.builders.user;

import com.graduation.system.data.dto.UserDTO;
import com.graduation.system.data.enums.UserRole;

import java.util.List;

public class UserDTOBuilder {
    private UserDTO user;

    public UserDTOBuilder(){
        this.user = new UserDTO();
    }

    public UserDTOBuilder id(Long id){
        this.user.setId(id);
        return this;
    }

    public UserDTOBuilder email(String email){
        this.user.setEmail(email);
        return this;
    }

    public UserDTOBuilder faculty(String faculty){
        this.user.setFaculty(faculty);
        return this;
    }

    public UserDTOBuilder firstName(String firstName){
        this.user.setFirstName(firstName);
        return this;
    }

    public UserDTOBuilder lastName(String lastName){
        this.user.setLastName(lastName);
        return this;
    }

    public UserDTOBuilder roles(List<UserRole> roles){
        this.user.setRole(roles);
        return this;
    }

    public UserDTOBuilder egn(String egn){
        this.user.setEgn(egn);
        return this;
    }

    public UserDTO getUser(){
        return this.user;
    }

    public void resetUser(){
        this.user = new UserDTO();
    }

}
