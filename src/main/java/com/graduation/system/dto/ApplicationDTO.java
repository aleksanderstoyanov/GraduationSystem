package com.graduation.system.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {

    private Long id;

    @NotNull
    private String subject;

    @NotNull
    private String task;

    @NotNull
    private String purpose;

    @NotNull
    private boolean approved;

    TeacherDTO teacher;
}
