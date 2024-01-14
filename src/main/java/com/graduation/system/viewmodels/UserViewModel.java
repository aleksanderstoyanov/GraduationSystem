package com.graduation.system.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserViewModel {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String faculty;
    private String role;
    public String getRole() {
        return role
                .replace('[', ' ')
                .replace(']', ' ');
    }
}
