package net.nomad.springbootrestfulwebservices.controller;

import lombok.AllArgsConstructor;
import net.nomad.springbootrestfulwebservices.dto.UserDto;
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
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/users/1
    // get User by Id REST API
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // http://localhost:8080/api/users/1
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable("id") Long userId,
                                               @RequestBody UserDto user) {
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully!", HttpStatus.OK);
    }
}
