package com.hacktober.blog.otp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hacktober.blog.email.EmailService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService; // reuse your existing email sender

    /**
     * Generate OTP and send it to the provided email.
     */
    @PostMapping("/generate")
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
    public Map<String, Object> verifyOtp(@RequestParam String email,
                                         @RequestParam String otp) {

    	// Verify OTP using OtpService
    	// Return appropriate response to frontend
        return response;
    }
}
