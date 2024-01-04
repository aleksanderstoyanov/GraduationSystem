package com.graduation.system.dto;

import com.graduation.system.messages.EntityMessages;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
public class RegisterDTO {
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull(message = EntityMessages.CommonMessage.NameNotNull)
    @Size(min = 4, max = 15, message = EntityMessages.CommonMessage.NameLength)
    private String firstName;

    @NotNull(message = EntityMessages.CommonMessage.NameNotNull)
    @Size(min = 4, max = 15, message = EntityMessages.CommonMessage.NameLength)
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^[0-9]{10}$", message = "EGN should be exactly 10 characters and with numerical values!")
    public String egn;

    @NotNull
    @NotEmpty
    public String faculty;
    @NotNull
    private boolean isStudent;

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }
}
