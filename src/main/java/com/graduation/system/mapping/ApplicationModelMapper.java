package com.graduation.system.mapping;

import com.graduation.system.dto.ApplicationDTO;
import com.graduation.system.viewmodels.ApplicationViewModel;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicationModelMapper extends BaseMapper{
    public ApplicationModelMapper(ModelMapper _mapper) {
        super(_mapper);
    }
}
