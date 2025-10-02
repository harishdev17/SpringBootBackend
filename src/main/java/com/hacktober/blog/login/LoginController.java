package com.hacktober.blog.login;

import com.hacktober.blog.exceptions.UnauthorizedException;
import com.hacktober.blog.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(@RequestParam String username,
                                     @RequestParam String password)
            throws InterruptedException, ExecutionException {

        boolean success = loginService.login(username, password);
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("login", success);

        if (success) {
            data.put("message", "Login successful");
            return ResponseEntity.ok(ApiResponse.success(data, "Login successful"));
        } else {
            throw new UnauthorizedException("Invalid username or password");
        }
    }
}
