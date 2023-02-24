package net.nomad.springbootrestfulwebservices.service.impl;

import lombok.AllArgsConstructor;
import net.nomad.springbootrestfulwebservices.dto.UserDto;
import net.nomad.springbootrestfulwebservices.entity.User;
import net.nomad.springbootrestfulwebservices.mapper.UserMapper;
import net.nomad.springbootrestfulwebservices.repository.UserRepository;
import net.nomad.springbootrestfulwebservices.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// this annotation for service class auto registered with Spring context and able to be injected into other components that depend on it
@Service
@AllArgsConstructor

// single parameter we can omit using @Autowired annotation for injection
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert UserDto into User JPA entity
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        // convert JPA into UserDto
        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        return savedUserDto;
    }


    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).get();
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
