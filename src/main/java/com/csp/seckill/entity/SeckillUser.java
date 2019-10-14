package com.csp.seckill.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/13 19:31
 */

@Getter
@Setter
public class SeckillUser {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
