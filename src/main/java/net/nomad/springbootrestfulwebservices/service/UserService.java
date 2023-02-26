package net.nomad.springbootrestfulwebservices.service;

import net.nomad.springbootrestfulwebservices.dto.UserDto;

import java.util.List;
import java.util.stream.Stream;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUser();

    UserDto updateUser(UserDto user);

    void deleteUser(Long userId);
}
