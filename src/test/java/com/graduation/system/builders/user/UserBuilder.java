package com.graduation.system.builders.user;

import com.graduation.system.data.dto.UserDTO;
import com.graduation.system.data.entity.User;
import com.graduation.system.data.enums.UserRole;

import java.util.List;

public class UserBuilder {
    private User user;

    public UserBuilder(){
        this.user = new User();
    }

    public UserBuilder id(Long id){
        this.user.setId(id);
        return this;
    }

    public UserBuilder username(String username){
        this.user.setUsername(username);
        return this;
    }
    public UserBuilder email(String email){
        this.user.setEmail(email);
        return this;
    }

    public UserBuilder password(String password){
        this.user.setPassword(password);
        return this;
    }

    public UserBuilder firstName(String firstName){
        this.user.setFirstName(firstName);
        return this;
    }

    public UserBuilder lastName(String lastName){
        this.user.setLastName(lastName);
        return this;
    }

    public UserBuilder egn(String egn){
        this.user.setEgn(egn);
        return this;
    }

    public User getUser(){
        return this.user;
    }

    public void resetUser(){
        this.user = new User();
    }
}
