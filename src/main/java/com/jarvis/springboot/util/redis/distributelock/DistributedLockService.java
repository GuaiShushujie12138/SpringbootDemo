package com.jarvis.springboot.util.redis.distributelock;

public interface DistributedLockService {

    /**
     * 获取分布式锁
     *
     * @param key
     * @param value
     * @param keyTtl
     * @return
     */
    boolean tryLock(String key, String value, Long keyTtl);

    /**
     * 释放分布式锁
     *
     * @param key
     * @param value
     * @return
     */
    boolean unLock(String key, String value);
}
