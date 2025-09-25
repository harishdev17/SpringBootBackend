package com.hacktober.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.HostAndPort;

@Configuration
public class RedisConfig {
	
	@Value("${redis.password}")
	private String password;

    @Bean
    public UnifiedJedis unifiedJedis() {
        JedisClientConfig config = DefaultJedisClientConfig.builder()
                .user("default")
                .password(password)
                .build();
        return new UnifiedJedis(
            new HostAndPort("redis-14860.crce182.ap-south-1-1.ec2.redns.redis-cloud.com", 14860),
            config
        );
    }
}
