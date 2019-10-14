package com.csp.seckill.redis;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/14 10:02
 */
public class SeckillUserKey extends BasePrefix {

    private static final int TOKEN_EXPIRE_TIME = 3600 * 24;

    public SeckillUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SeckillUserKey token = new SeckillUserKey(TOKEN_EXPIRE_TIME, "token");
}
