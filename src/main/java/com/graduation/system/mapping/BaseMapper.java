package com.graduation.system.mapping;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public abstract class BaseMapper {
    @Autowired
    protected final ModelMapper _mapper;

    public <T> Object mapToModel(T from, Class to){
        return _mapper.map(from, to);
    }
}
