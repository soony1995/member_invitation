package com.example.member_invitation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisServiceLettuce {

    private final RedisTemplate<String,String> redisTemplate;

    public void setStringOps(String key, String value, Duration ttl) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(key, value, ttl);
    }

    public String getStringOps(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean removeValue(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }
}