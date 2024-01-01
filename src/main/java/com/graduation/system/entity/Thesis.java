package com.graduation.system.entity;

import com.graduation.system.messages.EntityMessages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

import static com.graduation.system.messages.EntityMessages.*;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "diplomaThesis")
@Entity(name = "diplomaThesis")
public class Thesis extends BaseEntity {

    @Column(name = "text")
    @NotNull(message = CommonMessage.TitleNotNull)
    @Size(min = 10, max = 550, message = CommonMessage.TitleLength)
    private String title;

    @Column(name = "text", insertable = false, updatable = false)
    @NotNull(message = CommonMessage.TextNotNull)
    @Size(min = 10, max = 550, message = CommonMessage.TextLength)
    private String text;

    @Column(name = "submittedDate")
    @NotNull(message = CommonMessage.SubmittedDateNotNull)
    private LocalDate submittedDate;

    @OneToOne
    @JoinColumn(name = "review_id")
    private Review review;
}
