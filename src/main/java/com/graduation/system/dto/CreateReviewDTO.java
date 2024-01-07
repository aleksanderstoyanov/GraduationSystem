package com.graduation.system.dto;

import com.graduation.system.entity.Thesis;
import com.graduation.system.messages.EntityMessages;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewDTO {

    private Long thesisId;

    @Size(min = 10, max = 550, message = EntityMessages.CommonMessage.TextLength)
    private String text;


    @NotNull(message = EntityMessages.ReviewMessage.SummaryNotNull)
    @Size(min = 10, max = 550, message = EntityMessages.ReviewMessage.SummaryLength)
    private String summary;

    @NotNull
    private boolean granted;

}
