package com.hacktober.blog.email;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/mail")
@CrossOrigin("*")
@Tag(name = "Email", description = "Endpoints for sending transactional emails")
public class EmailController {
	
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    @Operation(summary = "Send email", description = "Dispatch an email message using the configured SMTP provider.")
    public String sendMail(@RequestBody EmailRequest request) {
        emailService.sendEmail(request.to, request.subject, request.body);
        return "Email sent successfully to " + request.to;
    }
}
