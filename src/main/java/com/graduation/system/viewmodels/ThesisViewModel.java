package com.graduation.system.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThesisViewModel {

    private Long id;

    private String title;

    private String text;

    private ReviewViewModel review;
}
