package com.hacktober.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EnvCheck implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("GMAIL_ACCOUNT=" + System.getenv("GMAIL_ACCOUNT"));
        System.out.println("GMAIL_APP_KEY=" + System.getenv("GMAIL_APP_KEY"));
    }
}
