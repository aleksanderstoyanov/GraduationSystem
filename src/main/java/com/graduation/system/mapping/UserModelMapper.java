package com.graduation.system.mapping;

import com.graduation.system.dto.UserDTO;
import com.graduation.system.entity.Role;
import com.graduation.system.entity.User;
import com.graduation.system.enums.UserRole;
import com.graduation.system.viewmodels.UserEditViewModel;
import com.graduation.system.viewmodels.UserViewModel;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserModelMapper extends BaseMapper {

    public UserModelMapper(ModelMapper _mapper) {
        super(_mapper);
    }

    public UserDTO mapToUserDTO(User user){
        Converter converter = convertRoles();

        if (_mapper.getTypeMap(User.class, UserDTO.class) == null){

            TypeMap<User, UserDTO> propertyMapper = this._mapper.createTypeMap(User.class, UserDTO.class);

            propertyMapper.addMappings(mapper -> mapper
                    .using(converter)
                    .map(src -> src.getRoles(), UserDTO :: setRole)
            );
        }

        return (UserDTO) mapToModel(user, UserDTO.class);
    }

    public UserViewModel mapToUserViewModel(UserDTO user){
        return (UserViewModel) mapToModel(user, UserViewModel.class);
    }

    public UserEditViewModel mapToUserEditViewModel(UserDTO user){
        return (UserEditViewModel) mapToModel(user, UserEditViewModel.class);
    }

    private Converter<List<Role>, List<UserRole>> convertRoles() {
        Converter<List<Role>, List<UserRole>> rolesConverter =
                ctx -> (List<UserRole>) ctx.getSource()
                        .stream()
                        .map(Role::getRole)
                        .collect(Collectors.toList());

        return rolesConverter;
    }

}
