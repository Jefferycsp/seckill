package com.csp.seckill.dao;

import com.csp.seckill.entity.SeckillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/13 19:52
 */

@Mapper
public interface SeckillUserDao {
    @Select("select * from seckill_user where id = #{id}")
    SeckillUser findById(@Param("id") long id);
}
