package com.graduation.system.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

import static com.graduation.system.messages.EntityMessages.*;
@Getter
@Setter
@AllArgsConstructor
@Embeddable
@Entity(name = "defenses")
@Table(name = "defenses")
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Teacher teacher;
}
