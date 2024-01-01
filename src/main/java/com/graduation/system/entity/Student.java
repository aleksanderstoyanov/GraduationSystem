package com.graduation.system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static com.graduation.system.messages.EntityMessages.*;

@Getter
@Setter
@Table
@Entity
@AllArgsConstructor
public class Student extends BaseEntity {

    @NotNull(message = StudentMessage.FnNotNull)
    @Size(min = 7, max = 7, message = StudentMessage.FnLength)
    @Column(name = "fn")
    private String fn;

    @NotNull(message = CommonMessage.NameNotNull)
    @Column(name = "name")
    @Size(min = 4, max = 15, message = CommonMessage.NameLength)
    private String name;

    @NotNull(message = CommonMessage.StartDateNotNull)
    private LocalDate startDate;

    @OneToMany(mappedBy = "student")
    private List<Application> applications;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Defense> defenses;
}
