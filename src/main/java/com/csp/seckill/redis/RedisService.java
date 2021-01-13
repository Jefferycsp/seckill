package com.csp.seckill.redis;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/13 14:48
 */

@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取key的值
     *
     * @param prefix
     * @param key
     * @return
     */
    public Object get(KeyPrefix prefix, String key) {
        String realKey = prefix.getPrefix() + key;
        return redisTemplate.opsForValue().get(realKey);
    }

    /**
     * 设置一个key value
     *
     * @param prefix
     * @param key
     * @param object
     * @return
     */
    public boolean set(KeyPrefix prefix, String key, Object object) {
        if (object == null) {
            return false;
        }
        String realKey = prefix.getPrefix() + key;
        int seconds = prefix.expireSeconds();
        if (seconds <= 0) {
            redisTemplate.opsForValue().set(realKey, object);
        } else {
            redisTemplate.opsForValue().set(realKey, object, seconds, TimeUnit.SECONDS);
        }
        return true;
    }

    /**
     * 判断某个key是否存在
     *
     * @param prefix
     * @param key
     * @return
     */
    public boolean exists(KeyPrefix prefix, String key) {
        String realKey = prefix.getPrefix() + key;
        return redisTemplate.hasKey(realKey);
    }


    /**
     * 增加某个key的值
     *
     * @param prefix
     * @param key
     * @return
     */
    public Long incr(KeyPrefix prefix, String key) {
        String realKey = prefix.getPrefix() + key;
        return redisTemplate.opsForValue().increment(realKey);
    }

    /**
     * 减少某个key的值
     *
     * @param prefix
     * @param key
     * @return
     */
    public Long decr(KeyPrefix prefix, String key) {
        String realKey = prefix.getPrefix() + key;
        return redisTemplate.opsForValue().decrement(realKey);
    }

    /**
     * 删除某个key的值
     *
     * @param prefix
     * @param key
     * @return
     */
    public boolean delete(KeyPrefix prefix, String key) {
        String realKey = prefix.getPrefix() + key;
        return redisTemplate.delete(realKey);
    }

    /**
     * 分布式锁
     *
     * @param prefix
     * @param key
     * @param object
     * @return
     */
    public boolean lock(KeyPrefix prefix, String key, Object object) {
        String realKey = prefix.getPrefix() + key;
        if (redisTemplate.opsForValue().setIfAbsent(realKey, object)) {
            return true;
        }
        return false;
        // 还未完成
    }
}
