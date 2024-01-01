package com.graduation.system.entity;

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
@Table( name = "diplomaReview")
@Entity(name = "diplomaReview")
public class Review extends BaseEntity {

    @Column(name = "submittedDate")
    @NotNull(message = CommonMessage.SubmittedDateNotNull)
    private LocalDate submittedDate;

    @Column(name = "text", insertable = false, updatable = false)
    @NotNull(message = CommonMessage.TextNotNull)
    @Size(min = 10, max = 550, message = CommonMessage.TextLength)
    private String text;

    @Column(name = "summary")
    @NotNull(message = ReviewMessage.SummaryNotNull)
    @Size(min = 10, max = 550, message = ReviewMessage.SummaryLength)
    private String summary;

    @OneToOne(mappedBy = "review")
    private Thesis thesis;
}
