package com.graduation.system.data.dto;

import com.graduation.system.data.enums.Position;
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
