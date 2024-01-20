package com.graduation.system.data.dto;

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
