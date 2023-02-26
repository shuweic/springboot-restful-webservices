package net.nomad.springbootrestfulwebservices.mapper;

import net.nomad.springbootrestfulwebservices.dto.UserDto;
import net.nomad.springbootrestfulwebservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

//    @Mapping(source = "email", target = "emailAddress")
    // when the mapping variable name is different

    // Mappers auto implementation for this interface
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
