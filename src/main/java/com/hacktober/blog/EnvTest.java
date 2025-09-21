package com.hacktober.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class EnvTest {

    @Value("${GMAIL_ACCOUNT}")
    private String gmailAccount;

    @PostConstruct
    public void print() {
        System.out.println("\n---------------------\nGMAIL_ACCOUNT is: " + gmailAccount + "\n--------------------\n");
    }
}

