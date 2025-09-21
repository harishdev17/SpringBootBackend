package com.hacktober.blog.user;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        return userService.create(user);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) throws InterruptedException, ExecutionException {
        return userService.getByUsername(username);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() throws InterruptedException, ExecutionException {
        return userService.getAll();
    }

    @PutMapping("/{username}")
    public String updateUser(@PathVariable String username, @RequestBody User user) throws InterruptedException, ExecutionException {
        user.setUsername(username); // ensure username remains the document ID
        return userService.update(user);
    }

    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable String username) throws InterruptedException, ExecutionException {
        return userService.delete(username);
    }
}
