package com.graduation.system.builders;

import com.graduation.system.data.dto.UserDTO;
import com.graduation.system.data.enums.UserRole;
import com.graduation.system.models.UserEditViewModel;

import java.util.List;

public class UserEditViewModelBuilder {
    private UserEditViewModel user;

    public UserEditViewModelBuilder(){
        this.user = new UserEditViewModel();
    }

    public UserEditViewModelBuilder email(String email){
        this.user.setEmail(email);
        return this;
    }

    public UserEditViewModelBuilder faculty(String faculty){
        this.user.setFaculty(faculty);
        return this;
    }

    public UserEditViewModelBuilder firstName(String firstName){
        this.user.setFirstName(firstName);
        return this;
    }

    public UserEditViewModelBuilder lastName(String lastName){
        this.user.setLastName(lastName);
        return this;
    }

    public UserEditViewModelBuilder roles(List<UserRole> roles){
        this.user.setRole(roles);
        return this;
    }

    public UserEditViewModelBuilder egn(String egn){
        this.user.setEgn(egn);
        return this;
    }

    public UserEditViewModel getUser(){
        return this.user;
    }

    public void resetUser(){
        this.user = new UserEditViewModel();
    }
}
