package com.graduation.system.services.impl;

import com.graduation.system.data.entity.Faculty;
import com.graduation.system.data.enums.FacultyType;
import com.graduation.system.data.repository.FacultyRepository;
import com.graduation.system.services.contracts.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private FacultyRepository _repository;

    @Override
    public Faculty getByName(String name){
        return _repository.findByName(name);
    }

    @Override
    public void create(String name){
        if (getByName(name) == null){
            Faculty faculty = new Faculty();
            faculty.setName(Enum.valueOf(FacultyType.class, name).name());
            _repository.save(faculty);
        }
    }
}
