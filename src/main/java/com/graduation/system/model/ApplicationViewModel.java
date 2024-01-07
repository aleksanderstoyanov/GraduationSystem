package com.graduation.system.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationViewModel {

    private Long id;

    private String subject;

    private String task;

    private String purpose;

    private boolean approved;
}
