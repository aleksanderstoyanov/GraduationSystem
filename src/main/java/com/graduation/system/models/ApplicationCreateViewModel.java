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
public class ApplicationCreateViewModel {
    @NotNull(message = EntityMessages.ApplicationMessage.SubjectNotNull)
    @Size(min = 5, max = 30, message = EntityMessages.ApplicationMessage.SubjectLength)
    private String subject;

    @NotNull(message = EntityMessages.ApplicationMessage.TaskNotNull)
    @Size(min = 5, max = 30, message = EntityMessages.ApplicationMessage.TaskLength)
    private String task;

    @NotNull(message = EntityMessages.ApplicationMessage.PurposeNotNull)
    @Size(min = 5, max = 50, message = EntityMessages.ApplicationMessage.PurposeLength)
    private String purpose;

    @NotNull
    private boolean approved;
}
