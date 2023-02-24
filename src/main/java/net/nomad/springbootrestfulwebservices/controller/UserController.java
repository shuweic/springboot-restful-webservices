package net.nomad.springbootrestfulwebservices.controller;

import lombok.AllArgsConstructor;
import net.nomad.springbootrestfulwebservices.entity.User;
import net.nomad.springbootrestfulwebservices.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RestController is combination of @Controller and @ResponseBody
// @Controller tell the class should be registered with Spring MVC to handle HTTP requests
// @ResponsBody can return the data directly and format and serialize it to the appropriate format based on the "Content-Type" header of response
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    // create User REST API
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/users/1
    // get User by Id REST API
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // http://localhost:8080/api/users/1
    @PutMapping("{id}")
    public ResponseEntity<User> updateUserById(@PathVariable("id") Long userId,
                                               @RequestBody User user) {
        user.setId(userId);
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully!", HttpStatus.OK);
    }
}
