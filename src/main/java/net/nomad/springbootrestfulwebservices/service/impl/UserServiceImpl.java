package net.nomad.springbootrestfulwebservices.service.impl;

import lombok.AllArgsConstructor;
import net.nomad.springbootrestfulwebservices.dto.UserDto;
import net.nomad.springbootrestfulwebservices.entity.User;
import net.nomad.springbootrestfulwebservices.exception.EmailAlreadyExistsException;
import net.nomad.springbootrestfulwebservices.exception.ResourceNotFoundException;
import net.nomad.springbootrestfulwebservices.mapper.AutoUserMapper;
import net.nomad.springbootrestfulwebservices.repository.UserRepository;
import net.nomad.springbootrestfulwebservices.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.crypto.ExemptionMechanismException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// this annotation for service class auto registered with Spring context and able to be injected into other components that depend on it
@Service
@AllArgsConstructor

// single parameter we can omit using @Autowired annotation for injection
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    // inject modelMapper
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert UserDto into User JPA entity
//        User user = UserMapper.mapToUser(userDto);

//        User user = modelMapper.map(userDto, User.class);

        Optional<User> OptionalUser = userRepository.findByEmail(userDto.getEmail());

        // isPresent exist?
        if (OptionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        // convert JPA into UserDto by ModelMapper
//        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        return savedUserDto;
    }


    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
//        return modelMapper.map(user, UserDto.class);

        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = userRepository.findAll();
//        return userList.stream().map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());

        // model mapper
//        return userList.stream().map((user -> modelMapper.map(user, UserDto.class)))
//                .collect(Collectors.toList());

        // mapstruct
        return userList.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
//        return UserMapper.mapToUserDto(updatedUser);
//        return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }
}
