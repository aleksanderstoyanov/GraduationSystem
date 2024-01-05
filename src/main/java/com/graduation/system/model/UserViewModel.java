package com.graduation.system.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserViewModel {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String faculty;

    private String role;
}
