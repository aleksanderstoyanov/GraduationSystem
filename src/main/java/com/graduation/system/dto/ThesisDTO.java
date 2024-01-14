package com.graduation.system.dto;

import com.graduation.system.viewmodels.ReviewViewModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThesisDTO {
    private Long id;
    private String title;
    private String text;
    private ReviewDTO review;
    private Long applicationId;
}
