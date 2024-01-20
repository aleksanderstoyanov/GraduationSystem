package com.graduation.system.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static com.graduation.system.messages.EntityMessages.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Teacher teacher;

    @OneToOne(mappedBy = "application", cascade = CascadeType.DETACH, orphanRemoval = true)
    public Thesis thesis;

    public boolean isApproved() {
        return approved;
    }
}
