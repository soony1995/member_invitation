package com.example.member_invitation.config;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@Slf4j
@Profile("local")
@Configuration
public class RedisServer {
    @Value("${spring.data.redis.port}")
        private int redisPort;

        private redis.embedded.RedisServer redisServer;

        @PostConstruct
        public void startRedis() throws IOException {
            redisServer = new redis.embedded.RedisServer(redisPort);
            redisServer.start();
        }

        @PreDestroy
        public void stopRedis() {
            if (redisServer != null){
                redisServer.stop();
            }
        }
}