package com.csp.seckill.redis;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/13 15:20
 */
public class UserKey extends BasePrefix{

    /**
     * 0代表永不过期
     *
     * @param prefix
     */
    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");

}
