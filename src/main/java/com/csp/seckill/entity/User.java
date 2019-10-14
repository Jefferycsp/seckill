package com.csp.seckill.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/12 22:40
 */

@Getter
@Setter
@ToString
public class User implements Serializable {
    private Integer id;
    private String name;
}
