package com.graduation.system.mapping;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TeacherModelMapper extends BaseMapper {
    public TeacherModelMapper(ModelMapper _mapper) {
        super(_mapper);
    }
}
