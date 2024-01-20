package com.graduation.system.data.dto;

import com.graduation.system.data.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String faculty;

    @NotNull
    private String egn;

    private String position;

    @NotNull
    private List<UserRole> role;

    TeacherDTO teacherDTO;

    StudentDTO studentDTO;
}
