package com.graduation.system.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicationModelMapper extends BaseMapper{
    public ApplicationModelMapper(ModelMapper _mapper) {
        super(_mapper);
    }
}
