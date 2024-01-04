package com.graduation.system.services.impl;

import com.graduation.system.entity.Faculty;
import com.graduation.system.enums.FacultyType;
import com.graduation.system.repository.FacultyRepository;
import com.graduation.system.services.contracts.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private FacultyRepository _repository;

    public Faculty getByName(String name){
        return _repository.findByName(name);
    }

    public void create(String name){
        if (getByName(name) == null){
            Faculty faculty = new Faculty();
            faculty.setName(Enum.valueOf(FacultyType.class, name).name());
            _repository.save(faculty);
        }
    }
}
