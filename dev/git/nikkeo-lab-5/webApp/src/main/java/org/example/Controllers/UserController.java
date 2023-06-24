package org.example.Controllers;

import org.example.Entities.V2User;
import org.example.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private PasswordEncoder encoder;
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping(value = "/getAll", produces = "application/json")
    public Iterable<V2User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/getById/{id}", produces = "application/json")
    public V2User getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }
    @PutMapping(value = "/save", produces = "application/json")
    public V2User saveUser(@RequestBody V2User user) throws Exception {
        return userService.saveUser(user);
    }

    @DeleteMapping(value = "/deleteBy/{id}", produces = "application/json")
    public void deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }

    public Optional<V2User> getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }
    @PutMapping(value = "/update", produces = "application/json")
    public V2User updateUser(@RequestBody V2User updatedUser) {
        return userService.updateUser(updatedUser);
    }
}
