package com.hacktober.blog.otp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hacktober.blog.email.EmailService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/otp")
@CrossOrigin("*")
@Tag(name = "One Time Password", description = "Endpoints for generating and validating OTP codes")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService; // reuse your existing email sender

    /**
     * Generate OTP and send it to the provided email.
     */
    @PostMapping("/generate")
    @Operation(summary = "Generate OTP", description = "Create a new OTP code for the provided email and send it via email.")
    public Map<String, Object> generateOtp(@RequestParam String email) {

        // Generate OTP using OtpService
    	// send an email using EmailService to the user with appropriate subject and body
    	// Return an appropriate response so that it can be sent to frontend
    	
        return null;
    }

    /**
     * Verify OTP for a given email.
     */
    @PostMapping("/verify")
    @Operation(summary = "Verify OTP", description = "Validate that the submitted OTP matches the generated token for the email.")
    public Map<String, Object> verifyOtp(@RequestParam String email,
                                         @RequestParam String otp) {

    	// Verify OTP using OtpService
    	// Return appropriate response to frontend
        return null;
    }
}
