package com.example.member_invitation.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

@Configuration
public class RedisServerConfig {
    private final RedisServer redisServer = new RedisServer();

    @PostConstruct
       public void initRedis() {
           try {
               redisServer.start();
           } catch (Exception e) {
               System.out.println("start"+e.getMessage());
           }
       }

    @PreDestroy
       public void destroyRedis() {
           try {
               redisServer.stop();
           } catch (Exception e) {
               System.out.println("end"+e.getMessage());
           }
       }
}
