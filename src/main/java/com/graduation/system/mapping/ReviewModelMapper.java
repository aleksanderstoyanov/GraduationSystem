package com.graduation.system.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewModelMapper extends BaseMapper{
    public ReviewModelMapper(ModelMapper _mapper) {
        super(_mapper);
    }
}
