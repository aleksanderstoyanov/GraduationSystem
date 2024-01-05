package com.graduation.system.dto;

import com.graduation.system.messages.EntityMessages;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminEditDTO {
    @NotNull
    @NotEmpty
    private String email;

    @NotNull(message = EntityMessages.CommonMessage.NameNotNull)
    @Size(min = 4, max = 15, message = EntityMessages.CommonMessage.NameLength)
    private String firstName;

    @NotNull(message = EntityMessages.CommonMessage.NameNotNull)
    @Size(min = 4, max = 15, message = EntityMessages.CommonMessage.NameLength)
    private String lastName;

//    @NotNull
//    @NotEmpty
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^[0-9]{10}$", message = "EGN should be exactly 10 characters and with numerical values!")
    private String egn;

    private String position;

    @NotNull
    @NotEmpty
    private String faculty;

    @NotNull
    @NotEmpty
    private String role;
}
