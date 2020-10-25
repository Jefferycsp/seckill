package com.csp.seckill.dao;

import com.csp.seckill.entity.OrderInfo;
import com.csp.seckill.entity.SeckillOrder;
import org.apache.ibatis.annotations.*;

/**
 * @description:
 * @author: csp52872
 * @date: 2020/10/25
 */
@Mapper
public interface OrderDao {

    @Select("select * from seckill_order where user_id=#{userId} and goods_id=#{goodsId}")
    SeckillOrder getSeckillOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_time)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createTime} )")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
    long insert(OrderInfo orderInfo);

    @Insert("insert into seckill_order (user_id, goods_id, order_id) values(#{userId}, #{goodsId}, #{orderId})")
    int insertSeckillOrder(SeckillOrder seckillOrder);
}
