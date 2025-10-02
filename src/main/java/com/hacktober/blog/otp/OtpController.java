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
        Map<String,Object> response=new HashMap<>();
        try {
            String otp = otpService.generateOtp(email);
            emailService.sendEmail(email, "Your OTP for Hacktober Blog",
                    "Use the following One-Time Password (OTP) to verify your email address:\n\n" +
                            "OTP: " + otp + "\n\n" +
                            "This OTP is valid for 5 minutes.\n\n" +
                            "If you did not request this, please ignore this email.\n\n" +
                            "Hacktober Blog Team");
            response.put("status", "success");
            response.put("message", "An OTP has been sent to " + email);
        }
        catch (Exception E){
            response.put("status", "error");
            response.put("message", "Failed to generate or send OTP. Please try again.");
        }
        return response;
    }

    /**
     * Verify OTP for a given email.
     */
    @PostMapping("/verify")
    @Operation(summary = "Verify OTP", description = "Validate that the submitted OTP matches the generated token for the email.")
    public Map<String, Object> verifyOtp(@RequestParam String email,
                                         @RequestParam String otp) {
        Map<String,Object>response=new HashMap<>();
        try {
            boolean success = otpService.verifyOtp(email.trim(), otp.trim());
            if (success) {
                response.put("status", "success");
                response.put("message", "OTP verified successfully.");
            } else {
                response.put("status", "failed");
                response.put("message", "Invalid or expired OTP. Please try again.");
            }
        }
        catch (Exception e){
            response.put("status", "error");
            response.put("message", "OTP verification could not be completed. Please try again.");
        }
        return response;
    }
}
