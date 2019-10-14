package com.csp.seckill.dao;

import com.csp.seckill.entity.User;
import com.csp.seckill.vo.LoginVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/12 22:41
 */

@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Insert("insert into user values(#{id},#{name})")
    void insert(User user);

}
