package com.csp.seckill.service;

import com.csp.seckill.dao.UserDao;
import com.csp.seckill.entity.User;
import com.csp.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/12 22:43
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findById(Integer id){
        return userDao.findById(id);
    }

    public void insert(User user) {
        userDao.insert(user);
        user.setId(3);
        user.setName("fd");
        userDao.insert(user);
    }
}
