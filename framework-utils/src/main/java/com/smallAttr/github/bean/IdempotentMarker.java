package com.smallAttr.github.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Author: chenGang
 * @Date: 2018/7/3 下午2:09
 * @Description:
 */
public class IdempotentMarker {

    private RedisTemplate<String,String> stringRedisTemplate;

    /**
     * Lock key path.
     */
    private String key;

    /**
     * Lock expiration in miliseconds.
     */
    private int expireMsecs = 60 * 1000;


    private static final Logger logger = LoggerFactory.getLogger(IdempotentMarker.class);

    /**
     * Detailed constructor lock expiration of 60000 msecs.
     *
     * @param stringRedisTemplate
     * @param key
     *            lock key (ex. account:1, ...)
     */
    public IdempotentMarker(RedisTemplate<String,String> stringRedisTemplate, String key) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.key = key;
    }


    public IdempotentMarker(RedisTemplate<String, String> stringRedisTemplate, String key, int expireMsecs) {
        this(stringRedisTemplate, key);
        this.expireMsecs = expireMsecs;
    }

    /**
     * Acquire lock.
     *
     * @return true if lock is acquired, false acquire timeouted
     * @throws InterruptedException
     *             in case of thread interruption
     */
    public boolean acquire() throws InterruptedException {
        return acquire(stringRedisTemplate);
    }

    /**
     * Acquire lock.
     *
     * @param stringRedisTemplate
     * @return true if lock is acquired
     * @throws InterruptedException
     *             in case of thread interruption
     */
    public boolean acquire(RedisTemplate<String,String> stringRedisTemplate) throws InterruptedException {
        long expires = System.currentTimeMillis() + expireMsecs + 1;
        String expiresStr = String.valueOf(expires);

        Boolean absent = stringRedisTemplate.boundValueOps(key).setIfAbsent(expiresStr);
        if (absent) {
            stringRedisTemplate.expire(key, expireMsecs, TimeUnit.MILLISECONDS);
        }
        logger.info("return value {}", absent);
        return absent;
    }
}
