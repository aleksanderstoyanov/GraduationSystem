package com.graduation.system.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ThesisModelMapper extends BaseMapper{
    public ThesisModelMapper(ModelMapper _mapper) {
        super(_mapper);
    }
}
