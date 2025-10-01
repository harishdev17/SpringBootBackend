package com.hacktober.blog.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
@Tag(name = "Authentication", description = "Endpoints for authenticating users")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    @Operation(summary = "Authenticate user", description = "Validate a username and password against stored credentials.")
    public Map<String, Object> login(@RequestParam String username,
                                     @RequestParam String password)
            throws InterruptedException, ExecutionException {

        boolean success = loginService.login(username, password);
        Map<String, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("login", success);

        if (success) {
            response.put("message", "Login successful");
        } else {
            response.put("message", "Invalid username or password");
        }
        return response;
    }
}
