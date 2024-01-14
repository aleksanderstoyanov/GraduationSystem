package com.graduation.system.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewViewModel {

    private Long id;
    private String text;
    private String summary;
    private boolean isGranted;
}
