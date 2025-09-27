package com.hacktober.blog.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
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
