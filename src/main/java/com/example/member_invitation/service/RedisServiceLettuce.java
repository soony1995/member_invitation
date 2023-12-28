package com.example.member_invitation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisServiceLettuce {

    private final RedisTemplate redisTemplate;

    public void setStringOps(String key, String value, Duration ttl) {
        redisTemplate.opsForValue().set(key, value, ttl);
    }

    public String getStringOps(String key) throws Exception {
        Object value = redisTemplate.opsForValue().get(key);
        return (String) value;
    }

    public boolean removeValue(String key) {
        return redisTemplate.delete(key);
    }
}