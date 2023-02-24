package net.nomad.springbootrestfulwebservices.service;

import net.nomad.springbootrestfulwebservices.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUserById(Long userId);

    List<User> getAllUser();

    User updateUser(User user);

    void deleteUser(Long userId);
}
