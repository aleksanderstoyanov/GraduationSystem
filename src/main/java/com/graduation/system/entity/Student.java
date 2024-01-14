package com.graduation.system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.graduation.system.messages.EntityMessages.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "students")
@Table(name = "students")
public class Student extends BaseEntity {

    @NotNull(message = StudentMessage.FnNotNull)
    @Size(min = 7, max = 7, message = StudentMessage.FnLength)
    @Column(name = "fn")
    private String fn;


    @Column(name="egn")
    @Size(min = 10, max = 10, message = "EGN should be exactly 10 symbols")
    private String egn;

    @OneToMany(mappedBy = "student", orphanRemoval = true)
    private List<Application> applications;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Defense> defenses;

    @OneToOne
    @JoinColumn(name = "user_egn")
    private User user;
}
