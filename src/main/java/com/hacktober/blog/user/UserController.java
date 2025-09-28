package com.hacktober.blog.user;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@Tag(name = "Users", description = "Endpoints for managing platform users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create user", description = "Persist a new user profile in the database.")
    public String createUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        return userService.create(user);
    }

    @GetMapping("/{username}")
    @Operation(summary = "Get user", description = "Retrieve a single user by username.")
    public User getUser(@PathVariable String username) throws InterruptedException, ExecutionException {
        return userService.getByUsername(username);
    }

    @GetMapping("/all")
    @Operation(summary = "List users", description = "Retrieve all registered users.")
    public List<User> getAllUsers() throws InterruptedException, ExecutionException {
        return userService.getAll();
    }

    @PutMapping("/{username}")
    @Operation(summary = "Update user", description = "Update a user's profile data, keeping the username immutable.")
    public String updateUser(@PathVariable String username, @RequestBody User user) throws InterruptedException, ExecutionException {
        user.setUsername(username); // ensure username remains the document ID
        return userService.update(user);
    }

    @DeleteMapping("/{username}")
    @Operation(summary = "Delete user", description = "Remove a user and their profile information.")
    public String deleteUser(@PathVariable String username) throws InterruptedException, ExecutionException {
        return userService.delete(username);
    }
}
