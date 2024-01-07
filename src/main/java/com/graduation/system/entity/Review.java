package com.graduation.system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.graduation.system.messages.EntityMessages.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
@Entity(name = "reviews")
public class Review extends BaseEntity {

    @Column(name = "submittedDate")
    @NotNull(message = CommonMessage.SubmittedDateNotNull)
    private LocalDate submittedDate = LocalDate.now();

    @Column(name = "text")
    @NotNull(message = CommonMessage.TextNotNull)
    @Size(min = 10, max = 550, message = CommonMessage.TextLength)
    private String text;

    @Column(name = "summary")
    @NotNull(message = ReviewMessage.SummaryNotNull)
    @Size(min = 10, max = 550, message = ReviewMessage.SummaryLength)
    private String summary;

    @Column(name = "granted")
    private boolean granted = false;

    @OneToOne(mappedBy = "review", cascade = CascadeType.DETACH, orphanRemoval = true)
    private Thesis thesis;
}
