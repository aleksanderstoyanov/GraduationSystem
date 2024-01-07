package com.graduation.system.services.impl;

import com.graduation.system.dto.AdminEditDTO;
import com.graduation.system.entity.*;
import com.graduation.system.enums.Position;
import com.graduation.system.repository.UserRepository;
import com.graduation.system.services.contracts.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private StudentServiceImpl _studentService;

    private RoleServiceImpl _roleService;
    private FacultyServiceImpl _facultyService;
    private TeacherServiceImpl _teacherService;
    private UserRepository _repository;

    private void detachStudent(String egn) throws Exception{
        Student student = _studentService.findByEgn(egn);

        if(student == null){
            return;
        }

        _studentService.deleteStudent(student);
    }

    private void detachTeacher(String egn) throws Exception{
        Teacher teacher = _teacherService.findByEgn(egn);

        if(teacher == null){
            return;
        }

        _teacherService.deleteTeacher(teacher);
    }
    private void updateStudent(AdminEditDTO editDTO) throws Exception{

        User user = _repository.findByEgn(editDTO.getEgn());

        if(user == null){
            throw new IllegalArgumentException();
        }

        Student student = new Student();

        student.setUser(user);
        student.setEgn(editDTO.getEgn());
        student.setFn(_studentService.generateFacultyNumber());


        if (_studentService.findByEgn(editDTO.getEgn()) == null){
            _studentService.createStudent(student);
        }
    }

    private void updateTeacher(AdminEditDTO editDTO) throws Exception{

        User user = _repository.findByEgn(editDTO.getEgn());

        if(user == null){
            throw new IllegalArgumentException();
        }

        Teacher teacher = new Teacher();

        teacher.setUser(user);
        teacher.setEgn(editDTO.getEgn());

        Position position = _teacherService.getPositionByName(editDTO.getPosition());

        if (position == null){
            throw new IllegalArgumentException();
        }

        teacher.setPosition(position);

        if (_teacherService.findByEgn(editDTO.getEgn()) == null){
            _teacherService.createTeacher(teacher);
        }
    }

    @Override
    public void updateUser(AdminEditDTO editDTO) throws Exception{

        User user = _repository.findByEgn(editDTO.getEgn());

        if(user == null){
            throw new IllegalArgumentException();
        }


        String userRole = user.getRoles().get(0).getRole().name();

        if (!userRole.equals(editDTO.getRole()))
        {
            switch (editDTO.getRole()){
                case "TEACHER":
                    detachStudent(editDTO.getEgn());
                    updateTeacher(editDTO);
                    break;
                case "STUDENT":
                    detachTeacher(editDTO.getEgn());
                    updateStudent(editDTO);
                    break;
            }
        }

        Faculty faculty = _facultyService.getByName(editDTO.getFaculty());

        if(faculty != null){
            user.setFaculty(faculty);
        }

        Role role = _roleService.getByRole(editDTO.getRole());

        if (role != null){
            List<Role> roles = user.getRoles();
            user.getRoles().removeAll(roles);
            _repository.save(user);
        }

        user.getRoles().add(role);
        user.setFirstName(editDTO.getFirstName());
        user.setLastName(editDTO.getLastName());
        user.setEmail(editDTO.getEmail());
        user.setEgn(editDTO.getEgn());

        _repository.save(user);
    }

    @Override
    public User findById(Long id){
        return _repository.findById(id).get();
    }

    @Override
    public List<User> findAllUsers(){
        return _repository.findAllUsersWithoutAdmin();
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        User user = findById(id);

        if (user == null){
            throw new IllegalArgumentException();
        }

        _repository.delete(user);
    }
}
