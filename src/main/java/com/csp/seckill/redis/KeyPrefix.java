package com.csp.seckill.redis;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/13 14:40
 */
public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();
}
