package net.nomad.springbootrestfulwebservices.service.impl;

import lombok.AllArgsConstructor;
import net.nomad.springbootrestfulwebservices.entity.User;
import net.nomad.springbootrestfulwebservices.repository.UserRepository;
import net.nomad.springbootrestfulwebservices.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

// this annotation for service class auto registered with Spring context and able to be injected into other components that depend on it
@Service
@AllArgsConstructor

// single parameter we can omit using @Autowired annotation for injection
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
