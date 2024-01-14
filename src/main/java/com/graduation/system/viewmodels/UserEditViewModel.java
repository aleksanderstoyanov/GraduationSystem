package com.graduation.system.viewmodels;

import com.graduation.system.enums.UserRole;
import com.graduation.system.messages.EntityMessages;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEditViewModel {

    @NotNull
    @NotEmpty
    private String email;

    @NotNull(message = EntityMessages.CommonMessage.NameNotNull)
    @Size(min = 4, max = 15, message = EntityMessages.CommonMessage.NameLength)
    private String firstName;

    @NotNull(message = EntityMessages.CommonMessage.NameNotNull)
    @Size(min = 4, max = 15, message = EntityMessages.CommonMessage.NameLength)
    private String lastName;

    @Size(min = 10, max = 10)
    @Pattern(regexp = "^[0-9]{10}$", message = "EGN should be exactly 10 characters and with numerical values!")
    private String egn;

    private String position;

    @NotNull
    @NotEmpty
    private String faculty;

    @NotNull
    @NotEmpty
    private List<UserRole> role;
}
