package com.hacktober.blog.email;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@CrossOrigin("*")
public class EmailController {
	
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendMail(@RequestBody EmailRequest request) {
        emailService.sendEmail(request.to, request.subject, request.body);
        return "Email sent successfully to " + request.to;
    }
}
