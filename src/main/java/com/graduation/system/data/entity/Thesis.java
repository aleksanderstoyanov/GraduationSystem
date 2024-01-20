package com.graduation.system.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static com.graduation.system.messages.EntityMessages.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "thesis")
@Entity(name = "thesis")
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
    private LocalDate submittedDate = LocalDate.now();

    @OneToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @OneToOne
    @JoinColumn(name = "review_id")
    private Review review;
}
