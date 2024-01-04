package com.example.member_invitation.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;


@Slf4j
@Configuration
public class RedisServerConfig {
    private final RedisServer redisServer;

    public RedisServerConfig() {
        this.redisServer = RedisServer.builder()
                .setting("maxmemory 128M")
                .build();
    }

    @PostConstruct
    public void startRedis() {
        try {
            this.redisServer.start();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @PreDestroy
    public void stopRedis() {
        this.redisServer.stop();
    }
}