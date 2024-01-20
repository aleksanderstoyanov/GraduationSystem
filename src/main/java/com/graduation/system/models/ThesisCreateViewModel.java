package com.graduation.system.models;

import com.graduation.system.messages.EntityMessages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThesisCreateViewModel {
    private Long applicationId;

    @NotNull(message = EntityMessages.CommonMessage.TitleNotNull)
    @Size(min = 10, max = 550, message = EntityMessages.CommonMessage.TitleLength)
    private String title;

    @NotNull(message = EntityMessages.CommonMessage.TextNotNull)
    @Size(min = 10, max = 550, message = EntityMessages.CommonMessage.TextLength)
    private String text;
}
