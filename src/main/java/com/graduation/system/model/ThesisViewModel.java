package com.graduation.system.model;

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

    private String thesisTitle;

    private String thesisText;
}
