package com.jarvis.springboot.util.redis.distributelock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.JedisCommands;

import java.util.Arrays;

import static redis.clients.jedis.params.SetParams.setParams;

/**
 * @author: jarvis.fu
 * <p>
 * redis 实现分布式锁
 */
@Slf4j
@Service
public class RedisDistributedLockService implements DistributedLockService {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long UNLOCK_SUCCESS = 1L;
    private static final String UNLOCK_LUA = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then\n" +
            "    return redis.call(\"del\",KEYS[1])\n" +
            "else\n" +
            "    return 0\n" +
            "end";

    private final RedisTemplate<String, String> redisTemplate;

    public RedisDistributedLockService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取分布式锁
     *
     * @param key
     * @param value
     * @param keyTtl
     * @return
     */
    @Override
    public boolean tryLock(String key, String value, Long keyTtl) {
        String result;

        try {
            result = redisTemplate.execute((RedisCallback<String>) connection -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.set(key, value, setParams().nx().px(keyTtl));
                // return commands.set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, keyTtl); // jedis 2.9.3 写法
            });
        } catch (Exception e) {
            log.warn("Try to lock key {} with value {} failed", key, value);
            return false;
        }

        return LOCK_SUCCESS.equals(result);
    }

    /**
     * 释放分布式锁
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean unLock(String key, String value) {
        Long result;

        try {
            result = redisTemplate.execute((RedisCallback<Long>) connection -> {
                // 目前只支持 redis 单机模式
                Jedis jedis = (Jedis) connection.getNativeConnection();
                return (Long) jedis.eval(UNLOCK_LUA, Arrays.asList(key), Arrays.asList(value));
            });
        } catch (Exception e) {
            log.warn("Try to unLock key {} with value {} failed", key, value);
            return false;
        }

        return UNLOCK_SUCCESS.equals(result);
    }
}
