package com.graduation.system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.graduation.system.messages.EntityMessages.*;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "applications")
@Entity(name = "applications")
public class Application extends BaseEntity {

    @Column(name = "subject")
    @NotNull(message = ApplicationMessage.SubjectNotNull)
    @Size(min = 5, max = 30, message = ApplicationMessage.SubjectLength)
    private String subject;

    @Column(name = "task")
    @NotNull(message = ApplicationMessage.TaskNotNull)
    @Size(min = 5, max = 30)
    private String task;

    @Column(name = "purpose")
    @NotNull(message = ApplicationMessage.PurposeNotNull)
    @Size(min = 5, max = 50, message = ApplicationMessage.PurposeLength)
    private String purpose;

    @Column(name = "approved")
    private boolean approved;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
