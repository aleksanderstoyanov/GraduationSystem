package com.graduation.system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

import static com.graduation.system.messages.EntityMessages.*;
@Getter
@Setter
@AllArgsConstructor
@Embeddable
@Entity
@Table(name = "diplomaDefense")
public class Defense {
    @EmbeddedId
    private DefenseId defenseId;

    @Column(name = "deleted")
    private boolean isDeleted = false;
    
    @Column(name = "grade")
    @Size(min = 2, max = 6, message = DefenseMessage.GradeValue)
    private int grade;

    @Column(name = "startDate")
    @NotNull(message = CommonMessage.StartDateNotNull)
    private LocalDate startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;
}
