package com.hacktober.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfig {
    // default executor is fine for this repo; add ThreadPoolTaskExecutor if needed later
}
