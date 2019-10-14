package com.csp.seckill.service;

import com.csp.seckill.dao.SeckillUserDao;
import com.csp.seckill.entity.SeckillUser;
import com.csp.seckill.exception.GlobalException;
import com.csp.seckill.redis.RedisService;
import com.csp.seckill.redis.SeckillUserKey;
import com.csp.seckill.result.CodeMsg;
import com.csp.seckill.util.MD5Util;
import com.csp.seckill.util.UUIDUtil;
import com.csp.seckill.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/13 19:48
 */

@Service
public class SeckillUserService {

    public static final String COOKIE_NAME = "token";

    @Autowired
    private SeckillUserDao seckillUserDao;

    @Autowired
    private RedisService redisService;

    public SeckillUser findById(long id) {
        return seckillUserDao.findById(id);
    }

    /**
     * 登录业务
     *
     * @param loginVo
     * @return
     */
    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        SeckillUser user = findById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(password, saltDB);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }

        // 生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return true;
    }

    /**
     * 从redis中取token对应的user信息
     *
     * @param token
     * @return
     */
    public SeckillUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        SeckillUser user = (SeckillUser) redisService.get(SeckillUserKey.token, token);
        if (user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    private void addCookie(HttpServletResponse response, String token, SeckillUser user) {
        redisService.set(SeckillUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME, token);
        cookie.setMaxAge(SeckillUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
