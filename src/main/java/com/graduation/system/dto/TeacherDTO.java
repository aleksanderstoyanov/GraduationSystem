package com.graduation.system.dto;

import com.graduation.system.enums.Position;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDTO {
    private Long id;
    private Position Position;
    private String egn;
    UserDTO userDTO;
}
